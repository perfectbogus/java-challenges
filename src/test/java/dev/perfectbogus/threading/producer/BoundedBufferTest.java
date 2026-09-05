package dev.perfectbogus.threading.producer;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoundedBufferTest {

    // --- Basic single-threaded sanity checks -------------------------------

    @Test
    @Timeout(2)
    void putThenTake_returnsSameItem() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        buffer.put(Optional.of(42));
        Optional<Integer> result = buffer.take();
        assertEquals(Optional.of(42), result);
    }

    @Test
    @Timeout(2)
    void fifoOrder_isPreserved_singleThread() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        for (int i = 0; i < 5; i++) {
            buffer.put(Optional.of(i));
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(Optional.of(i), buffer.take());
        }
    }

    @Test
    @Timeout(2)
    void poisonPill_isReturnedAsEmpty() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        buffer.put(Optional.empty());
        Optional<Integer> result = buffer.take();
        assertTrue(result.isEmpty());
    }

    // --- Blocking behavior ---------------------------------------------------

    @Test
    @Timeout(5)
    void take_blocksWhenEmpty_untilPutHappens() throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        AtomicBoolean tookItem = new AtomicBoolean(false);
        CountDownLatch consumerStarted = new CountDownLatch(1);

        Thread consumer = new Thread(() -> {
            consumerStarted.countDown();
            buffer.take(); // should block until main puts something
            tookItem.set(true);
        });
        consumer.start();
        consumerStarted.await();

        // Give the consumer a moment to actually enter wait(); if it wrongly
        // returned early, tookItem would already be true here.
        Thread.sleep(300);
        assertFalse(tookItem.get(), "take() should still be blocked on an empty buffer");

        buffer.put(Optional.of(1));
        consumer.join(2000);
        assertTrue(tookItem.get(), "take() should have unblocked after put()");
    }

    @Test
    @Timeout(5)
    void put_blocksWhenFull_untilTakeHappens() throws InterruptedException {
        int capacity = 3;
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.put(Optional.of(i)); // fill it up, none of these should block
        }

        AtomicBoolean producedExtra = new AtomicBoolean(false);
        CountDownLatch producerStarted = new CountDownLatch(1);

        Thread producer = new Thread(() -> {
            producerStarted.countDown();
            buffer.put(Optional.of(999)); // buffer is full, should block
            producedExtra.set(true);
        });
        producer.start();
        producerStarted.await();

        Thread.sleep(300);
        assertFalse(producedExtra.get(), "put() should still be blocked on a full buffer");

        buffer.take(); // frees exactly one slot
        producer.join(2000);
        assertTrue(producedExtra.get(), "put() should have unblocked after take()");
    }

    @Test
    @Timeout(5)
    void poisonPill_doesNotBlock_evenWhenBufferIsFull() throws InterruptedException {
        int capacity = 2;
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(capacity);
        buffer.put(Optional.of(1));
        buffer.put(Optional.of(2)); // buffer now full

        AtomicBoolean pillInserted = new AtomicBoolean(false);
        Thread pillProducer = new Thread(() -> {
            buffer.put(Optional.empty()); // must NOT block despite full buffer
            pillInserted.set(true);
        });
        pillProducer.start();
        pillProducer.join(1000);

        assertTrue(pillInserted.get(), "poison pill insertion should bypass capacity blocking");
    }

    // --- Multi-producer / multi-consumer end-to-end correctness -------------

    @Test
    @Timeout(30)
    void multiProducerMultiConsumer_deliversAllItemsExactlyOnce() throws InterruptedException {
        int producerCount = 3;
        int consumerCount = 2;
        int itemsPerProducer = 1000;

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        AtomicLong sum = new AtomicLong(0);
        List<Integer> consumed = new CopyOnWriteArrayList<>();

        Thread[] producers = new Thread[producerCount];
        for (int p = 0; p < producerCount; p++) {
            final int producerId = p;
            producers[p] = new Thread(() -> {
                int start = producerId * itemsPerProducer;
                for (int i = start; i < start + itemsPerProducer; i++) {
                    buffer.put(Optional.of(i));
                }
            });
        }

        Thread[] consumers = new Thread[consumerCount];
        for (int c = 0; c < consumerCount; c++) {
            consumers[c] = new Thread(() -> {
                while (true) {
                    Optional<Integer> item = buffer.take();
                    if (item.isEmpty()) {
                        break;
                    }
                    sum.addAndGet(item.get());
                    consumed.add(item.get());
                }
            });
        }

        for (Thread t : producers) t.start();
        for (Thread t : consumers) t.start();
        for (Thread t : producers) t.join();
        for (int c = 0; c < consumerCount; c++) {
            buffer.put(Optional.empty());
        }
        for (Thread t : consumers) t.join();

        long expectedCount = (long) producerCount * itemsPerProducer;
        long expectedSum = 0;
        for (int i = 0; i < expectedCount; i++) {
            expectedSum += i;
        }

        assertEquals(expectedCount, consumed.size());
        assertEquals(expectedSum, sum.get());

        // No duplicates and no missing values: every expected int appears exactly once.
        boolean[] seen = new boolean[(int) expectedCount];
        for (int value : consumed) {
            assertFalse(seen[value], "Duplicate value delivered: " + value);
            seen[value] = true;
        }
        for (int i = 0; i < seen.length; i++) {
            assertTrue(seen[i], "Missing value: " + i);
        }
    }

    @Test
    @Timeout(15)
    void allConsumers_receiveExactlyOnePoisonPill_andTerminate() throws InterruptedException {
        int consumerCount = 4;
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        AtomicLong terminatedConsumers = new AtomicLong(0);

        Thread[] consumers = new Thread[consumerCount];
        for (int c = 0; c < consumerCount; c++) {
            consumers[c] = new Thread(() -> {
                while (buffer.take().isPresent()) {
                    // discard real data, if any
                }
                terminatedConsumers.incrementAndGet();
            });
            consumers[c].start();
        }

        for (int c = 0; c < consumerCount; c++) {
            buffer.put(Optional.empty());
        }

        for (Thread t : consumers) {
            t.join(5000);
        }

        assertEquals(consumerCount, terminatedConsumers.get(),
                "Every consumer should receive exactly one pill and terminate");
    }

    // --- Repeated run to catch flaky race conditions -------------------------

    @RepeatedTest(20)
    @Timeout(10)
    void smallScaleRun_repeatedly_isAlwaysConsistent() throws InterruptedException {
        int producerCount = 2;
        int consumerCount = 2;
        int itemsPerProducer = 200;

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(4);
        AtomicLong count = new AtomicLong(0);

        Thread[] producers = new Thread[producerCount];
        for (int p = 0; p < producerCount; p++) {
            producers[p] = new Thread(() -> {
                for (int i = 0; i < itemsPerProducer; i++) {
                    buffer.put(Optional.of(i));
                }
            });
        }

        Thread[] consumers = new Thread[consumerCount];
        for (int c = 0; c < consumerCount; c++) {
            consumers[c] = new Thread(() -> {
                while (buffer.take().isPresent()) {
                    count.incrementAndGet();
                }
            });
        }

        for (Thread t : producers) t.start();
        for (Thread t : consumers) t.start();
        for (Thread t : producers) t.join();
        for (int c = 0; c < consumerCount; c++) {
            buffer.put(Optional.empty());
        }
        for (Thread t : consumers) t.join(5000);

        assertEquals((long) producerCount * itemsPerProducer, count.get());
    }
}
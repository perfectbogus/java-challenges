package dev.perfectbogus.threading.producer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ProducerConsumerDemo {

    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 3;
    private static final int ITEMS_PER_PRODUCER = 1000;

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        AtomicLong sum = new AtomicLong(0);
        List<Integer> consumed = new CopyOnWriteArrayList<>();

        Thread[] producers = new Thread[PRODUCER_COUNT];
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            final int producerId = i;
            producers[i] = new Thread(() -> {
                int start = producerId * ITEMS_PER_PRODUCER;
                for (int j = 0; j < start + ITEMS_PER_PRODUCER; j++ ) {
                    buffer.put(Optional.of(j));
                }
                buffer.put(Optional.empty());
            }, "producer-" + i);
        }

        Thread[] consumers = new Thread[CONSUMER_COUNT];
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            consumers[i] = new Thread(() -> {
                while (true) {
                    Optional<Integer> item = buffer.take();
                    if (item.isEmpty()) {
                        break;
                    }
                    sum.addAndGet(item.get());
                    consumed.add(item.get());
                }
            }, "consumer-" + i);
        }

        for (Thread p : producers) p.start();
        for (Thread c : consumers) c.start();

        for (Thread p : producers) p.join();

        for (int c = 0; c < CONSUMER_COUNT; c++) {
            buffer.put(Optional.empty());
        }

        for (Thread c : consumers) c.join();
    }
}

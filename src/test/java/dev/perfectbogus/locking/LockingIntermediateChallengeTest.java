package dev.perfectbogus.locking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

class LockingIntermediateChallengeTest {

    private static void runConcurrent(int threads, int iterationsPerThread, Runnable action) throws InterruptedException {
        Thread[] ts = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < iterationsPerThread; j++) action.run();
            });
        }
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();
    }

    // ==========================================================
    // CHALLENGE 1: incrementSynchronized
    // ==========================================================
    @Nested
    class IncrementSynchronizedTests {

        @Test
        void testNoLostUpdatesUnderConcurrency() throws InterruptedException {
            LockingIntermediateChallenge.SyncCounter counter = new LockingIntermediateChallenge.SyncCounter();
            runConcurrent(20, 1000, counter::incrementSynchronized);
            assertEquals(20000, counter.getCount());
        }
    }

    // ==========================================================
    // CHALLENGE 2: incrementWithBlock
    // ==========================================================
    @Nested
    class IncrementWithBlockTests {

        @Test
        void testNoLostUpdatesUnderConcurrency() throws InterruptedException {
            LockingIntermediateChallenge.SyncCounter counter = new LockingIntermediateChallenge.SyncCounter();
            runConcurrent(20, 1000, counter::incrementWithBlock);
            assertEquals(20000, counter.getCount());
        }
    }

    // ==========================================================
    // CHALLENGE 3: incrementWithLock
    // ==========================================================
    @Nested
    class IncrementWithLockTests {

        @Test
        void testNoLostUpdatesUnderConcurrency() throws InterruptedException {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            runConcurrent(20, 1000, counter::incrementWithLock);
            assertEquals(20000, counter.getCount());
        }
    }

    // ==========================================================
    // CHALLENGE 4: recursiveSum
    // ==========================================================
    @Nested
    class RecursiveSumTests {

        @Test
        void testPositiveN() {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            assertEquals(15, counter.recursiveSum(5));
        }

        @Test
        void testZero() {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            assertEquals(0, counter.recursiveSum(0));
        }

        @Test
        void testNegative() {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            assertEquals(0, counter.recursiveSum(-3));
        }
    }

    // ==========================================================
    // CHALLENGE 5: tryIncrementIfAvailable
    // ==========================================================
    @Nested
    class TryIncrementIfAvailableTests {

        @Test
        void testReturnsFalseWhileLockHeldThenTrueAfterRelease() throws InterruptedException {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            Thread holder = new Thread(() -> {
                try { counter.holdLockFor(300); } catch (InterruptedException ignored) {}
            });
            holder.start();
            Thread.sleep(50);

            boolean duringHold = counter.tryIncrementIfAvailable();
            holder.join();
            boolean afterRelease = counter.tryIncrementIfAvailable();

            assertFalse(duringHold);
            assertTrue(afterRelease);
            assertEquals(1, counter.getCount());
        }
    }

    // ==========================================================
    // CHALLENGE 6: incrementWithTimeout
    // ==========================================================
    @Nested
    class IncrementWithTimeoutTests {

        @Test
        void testShortTimeoutFailsWhileLockHeld() throws InterruptedException {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            Thread holder = new Thread(() -> {
                try { counter.holdLockFor(300); } catch (InterruptedException ignored) {}
            });
            holder.start();
            Thread.sleep(50);

            boolean acquired = counter.incrementWithTimeout(50);
            holder.join();

            assertFalse(acquired);
        }

        @Test
        void testLongTimeoutSucceedsOnceLockReleased() throws InterruptedException {
            LockingIntermediateChallenge.LockCounter counter = new LockingIntermediateChallenge.LockCounter();
            Thread holder = new Thread(() -> {
                try { counter.holdLockFor(200); } catch (InterruptedException ignored) {}
            });
            holder.start();
            Thread.sleep(50);

            boolean acquired = counter.incrementWithTimeout(2000);
            holder.join();

            assertTrue(acquired);
        }
    }

    // ==========================================================
    // CHALLENGE 7 & 8: put / take
    // ==========================================================
    @Nested
    class BoundedBufferTests {

        @Test
        void testProducerConsumerDeliversAllItemsInOrder() throws InterruptedException {
            LockingIntermediateChallenge.BoundedBuffer buffer = new LockingIntermediateChallenge.BoundedBuffer(3);
            int itemCount = 50;
            List<Integer> received = new CopyOnWriteArrayList<>();

            Thread producer = new Thread(() -> {
                try {
                    for (int i = 0; i < itemCount; i++) buffer.put(i);
                } catch (InterruptedException ignored) {}
            });
            Thread consumer = new Thread(() -> {
                try {
                    for (int i = 0; i < itemCount; i++) received.add(buffer.take());
                } catch (InterruptedException ignored) {}
            });

            producer.start();
            consumer.start();
            producer.join(5000);
            consumer.join(5000);

            assertEquals(itemCount, received.size());
            for (int i = 0; i < itemCount; i++) {
                assertEquals(i, received.get(i));
            }
            assertEquals(0, buffer.size());
        }
    }

    // ==========================================================
    // CHALLENGE 9: transfer
    // ==========================================================
    @Nested
    class TransferTests {

        @Test
        void testConcurrentOppositeTransfersDoNotDeadlockAndConserveBalance() throws InterruptedException {
            LockingIntermediateChallenge.Account accountA = new LockingIntermediateChallenge.Account(1, 1000);
            LockingIntermediateChallenge.Account accountB = new LockingIntermediateChallenge.Account(2, 1000);
            int rounds = 2000;

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < rounds; i++) {
                    LockingIntermediateChallenge.transfer(accountA, accountB, 10);
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < rounds; i++) {
                    LockingIntermediateChallenge.transfer(accountB, accountA, 10);
                }
            });

            t1.start();
            t2.start();
            t1.join(10000);
            t2.join(10000);

            assertFalse(t1.isAlive(), "transfer appears to have deadlocked");
            assertFalse(t2.isAlive(), "transfer appears to have deadlocked");
            assertEquals(2000, accountA.getBalance() + accountB.getBalance());
        }
    }

    // ==========================================================
    // CHALLENGE 10: unlockIfHeld
    // ==========================================================
    @Nested
    class UnlockIfHeldTests {

        @Test
        void testUnlocksWhenHeldByCurrentThread() {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            LockingIntermediateChallenge.unlockIfHeld(lock);
            assertFalse(lock.isLocked());
        }

        @Test
        void testDoesNothingWhenNotHeldByAnyone() {
            ReentrantLock lock = new ReentrantLock();
            assertDoesNotThrow(() -> LockingIntermediateChallenge.unlockIfHeld(lock));
            assertFalse(lock.isLocked());
        }

        @Test
        void testDoesNotUnlockWhenHeldByAnotherThread() throws InterruptedException {
            ReentrantLock lock = new ReentrantLock();
            Thread otherHolder = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            });
            otherHolder.start();
            Thread.sleep(50);

            LockingIntermediateChallenge.unlockIfHeld(lock);
            assertTrue(lock.isLocked());

            otherHolder.join();
        }
    }
}
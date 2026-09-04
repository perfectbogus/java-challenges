package dev.perfectbogus.locking;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LockingChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — synchronized method counter
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @RepeatedTest(5)
        void alwaysExact() throws InterruptedException {
            assertEquals(10000, LockingChallenges.challenge1(10, 1000));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(500, LockingChallenges.challenge1(1, 500));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(50000, LockingChallenges.challenge1(50, 1000));
        }

        @Test
        void singleIncrement() throws InterruptedException {
            assertEquals(1, LockingChallenges.challenge1(1, 1));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge1(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — synchronized block bank account
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @RepeatedTest(5)
        void balanceUnchangedAfterEqualDepositsAndWithdrawals()
                throws InterruptedException {
            assertEquals(1000.0,
                    LockingChallenges.challenge2(10, 1000.0), 0.001);
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(500.0,
                    LockingChallenges.challenge2(1, 500.0), 0.001);
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(2000.0,
                    LockingChallenges.challenge2(50, 2000.0), 0.001);
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge2(0, 1000.0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — ReentrantLock counter
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @RepeatedTest(5)
        void alwaysExact() throws InterruptedException {
            assertEquals(10000, LockingChallenges.challenge3(10, 1000));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(500, LockingChallenges.challenge3(1, 500));
        }

        @Test
        void highContention() throws InterruptedException {
            assertEquals(100000, LockingChallenges.challenge3(100, 1000));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge3(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — tryLock stats
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @RepeatedTest(5)
        void totalEqualsThreadCount() throws InterruptedException {
            LockingChallenges.TryLockStats stats =
                    LockingChallenges.challenge4(10);

            assertEquals(10, stats.total());
            assertEquals(10, stats.acquired() + stats.missed());
        }

        @Test
        void atLeastOneAcquired() throws InterruptedException {
            LockingChallenges.TryLockStats stats =
                    LockingChallenges.challenge4(10);

            assertTrue(stats.acquired() >= 1);
        }

        @Test
        void singleThread() throws InterruptedException {
            LockingChallenges.TryLockStats stats =
                    LockingChallenges.challenge4(1);

            assertEquals(1, stats.acquired()); // only thread must get lock!
            assertEquals(0, stats.missed());
            assertEquals(1, stats.total());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge4(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Condition await/signal bounded buffer
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() throws InterruptedException {
            assertEquals(15L, LockingChallenges.challenge5(List.of(1,2,3,4,5)));
        }

        @Test
        void singleItem() throws InterruptedException {
            assertEquals(42L, LockingChallenges.challenge5(List.of(42)));
        }

        @Test
        void largeList() throws InterruptedException {
            List<Integer> items = new ArrayList<>();
            for (int i = 1; i <= 100; i++) items.add(i);
            assertEquals(5050L, LockingChallenges.challenge5(items));
        }

        @RepeatedTest(3)
        void alwaysCorrect() throws InterruptedException {
            assertEquals(10L, LockingChallenges.challenge5(List.of(1,2,3,4)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — ReadWriteLock cache
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @RepeatedTest(5)
        void cacheSizeAlwaysThree() throws InterruptedException {
            assertEquals(3, LockingChallenges.challenge6(5, 3));
        }

        @Test
        void manyReaders() throws InterruptedException {
            assertEquals(3, LockingChallenges.challenge6(20, 2));
        }

        @Test
        void manyWriters() throws InterruptedException {
            assertEquals(3, LockingChallenges.challenge6(2, 20));
        }

        @Test
        void singleReaderWriter() throws InterruptedException {
            assertEquals(3, LockingChallenges.challenge6(1, 1));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge6(0, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — StampedLock optimistic read
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @RepeatedTest(5)
        void readsConsistentPoint() throws InterruptedException {
            double[] result = LockingChallenges.challenge7(3.0, 4.0);

            assertEquals(2, result.length);
            assertEquals(3.0, result[0], 0.001);
            assertEquals(4.0, result[1], 0.001);
        }

        @Test
        void differentCoordinates() throws InterruptedException {
            double[] result = LockingChallenges.challenge7(10.0, 20.0);

            assertEquals(10.0, result[0], 0.001);
            assertEquals(20.0, result[1], 0.001);
        }

        @Test
        void zeroCoordinates() throws InterruptedException {
            double[] result = LockingChallenges.challenge7(0.0, 0.0);

            assertEquals(0.0, result[0], 0.001);
            assertEquals(0.0, result[1], 0.001);
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Lock ordering prevents deadlock
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @RepeatedTest(5)
        void balancesUnchanged() throws InterruptedException {
            LockingChallenges.TransferResult result =
                    LockingChallenges.challenge8(1000.0, 1000.0, 100.0, 10);

            assertEquals(1000.0, result.balance1(), 0.001);
            assertEquals(1000.0, result.balance2(), 0.001);
        }

        @Test
        void singleTransfer() throws InterruptedException {
            LockingChallenges.TransferResult result =
                    LockingChallenges.challenge8(500.0, 500.0, 100.0, 2);

            assertEquals(500.0, result.balance1(), 0.001);
            assertEquals(500.0, result.balance2(), 0.001);
        }

        @Test
        void noDeadlock() throws InterruptedException {
            // If deadlock → test hangs → JUnit timeout → fail
            LockingChallenges.TransferResult result =
                    LockingChallenges.challenge8(2000.0, 2000.0, 200.0, 20);

            assertNotNull(result); // ← completed without deadlock!
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge8(1000, 1000, 100, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Fair ReentrantLock
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void allThreadsAcquireLock() throws InterruptedException {
            List<Integer> order = LockingChallenges.challenge9(5);

            assertEquals(5, order.size());
        }

        @Test
        void containsAllThreadIndices() throws InterruptedException {
            List<Integer> order = LockingChallenges.challenge9(5);

            assertTrue(order.containsAll(List.of(0,1,2,3,4)));
        }

        @Test
        void noDuplicates() throws InterruptedException {
            List<Integer> order = LockingChallenges.challenge9(5);

            assertEquals(order.size(), new HashSet<>(order).size());
        }

        @RepeatedTest(3)
        void tenThreadsAllAcquire() throws InterruptedException {
            List<Integer> order = LockingChallenges.challenge9(10);

            assertEquals(10, order.size());
            assertEquals(10, new HashSet<>(order).size());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> LockingChallenges.challenge9(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — LockSupport park/unpark coordination
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void correctLogEntries() throws InterruptedException {
            List<String> log = LockingChallenges.challenge10();

            assertTrue(log.contains("worker: started"));
            assertTrue(log.contains("worker: resumed"));
            assertTrue(log.contains("worker: done"));
            assertTrue(log.contains("main: started"));
            assertTrue(log.contains("main: unparking"));
            assertTrue(log.contains("main: done"));
        }

        @Test
        void workerStartsBeforeUnpark() throws InterruptedException {
            List<String> log = LockingChallenges.challenge10();

            int workerStart = log.indexOf("worker: started");
            int mainUnpark  = log.indexOf("main: unparking");
            assertTrue(workerStart < mainUnpark,
                    "Worker must start before main unparks it!");
        }

        @Test
        void resumedAfterUnpark() throws InterruptedException {
            List<String> log = LockingChallenges.challenge10();

            int unpark  = log.indexOf("main: unparking");
            int resumed = log.indexOf("worker: resumed");
            assertTrue(unpark < resumed,
                    "Worker must resume AFTER being unparked!");
        }

        @Test
        void doneIsLast() throws InterruptedException {
            List<String> log = LockingChallenges.challenge10();

            assertEquals("main: done", log.get(log.size() - 1),
                    "main: done should be the last entry!");
        }

        @RepeatedTest(3)
        void alwaysSixEntries() throws InterruptedException {
            assertEquals(6, LockingChallenges.challenge10().size());
        }
    }
}
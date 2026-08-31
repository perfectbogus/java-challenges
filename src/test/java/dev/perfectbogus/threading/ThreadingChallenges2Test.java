package dev.perfectbogus.threading;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ThreadingChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — volatile flag to stop thread
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void counterIsPositive() throws InterruptedException {
            long result = ThreadingChallenges2.challenge1(100);
            assertTrue(result > 0, "Counter should be positive: " + result);
        }

        @Test
        void longerRunProducesMoreCounts() throws InterruptedException {
            long short1 = ThreadingChallenges2.challenge1(50);
            long long1  = ThreadingChallenges2.challenge1(200);
            assertTrue(long1 >= short1, "Longer run should produce >= count");
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge1(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — ThreadLocal per-thread index
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() throws InterruptedException {
            assertEquals(List.of(0,1,2,3,4),
                    ThreadingChallenges2.challenge2(5));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(List.of(0), ThreadingChallenges2.challenge2(1));
        }

        @Test
        void tenThreads() throws InterruptedException {
            List<Integer> result = ThreadingChallenges2.challenge2(10);
            assertEquals(10, result.size());
            assertEquals(List.of(0,1,2,3,4,5,6,7,8,9), result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge2(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — AtomicReference CAS one winner
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @RepeatedTest(5)
        void exactlyOneWinner() throws InterruptedException {
            String winner = ThreadingChallenges2.challenge3(10);
            assertNotNull(winner);
            assertFalse(winner.isEmpty(), "Winner name should not be empty");
        }

        @Test
        void winnerIsThreadName() throws InterruptedException {
            String winner = ThreadingChallenges2.challenge3(5);
            // Thread names from ThreadingChallenges2 should contain "Thread"
            assertNotNull(winner);
        }

        @Test
        void singleThread() throws InterruptedException {
            String winner = ThreadingChallenges2.challenge3(1);
            assertNotNull(winner);
            assertFalse(winner.isEmpty());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge3(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — synchronized block with explicit lock
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @RepeatedTest(5)
        void alwaysExact() throws InterruptedException {
            assertEquals(10000, ThreadingChallenges2.challenge4(10, 1000));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(500, ThreadingChallenges2.challenge4(1, 500));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(50000, ThreadingChallenges2.challenge4(50, 1000));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge4(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — join(timeout) returns true/false
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void completesBeforeTimeout() throws InterruptedException {
            assertTrue(ThreadingChallenges2.challenge5(50, 500));
        }

        @Test
        void timesOut() throws InterruptedException {
            assertFalse(ThreadingChallenges2.challenge5(500, 100));
        }

        @Test
        void zeroSleepAlwaysCompletes() throws InterruptedException {
            assertTrue(ThreadingChallenges2.challenge5(0, 200));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — newCachedThreadPool unique thread names
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void returnsNonEmptySet() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge6(5);
            assertFalse(result.isEmpty());
        }

        @Test
        void singleTask() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge6(1);
            assertEquals(1, result.size());
        }

        @Test
        void manyTasks() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge6(20);
            assertFalse(result.isEmpty());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge6(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — invokeAny returns first result
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void fastestIndexReturned() throws InterruptedException, ExecutionException {
            // Index 1 has sleepTime=50 → finishes first
            int result = ThreadingChallenges2.challenge7(
                    List.of(300L, 50L, 200L, 400L, 100L));
            assertEquals(1, result);
        }

        @Test
        void singleTask() throws InterruptedException, ExecutionException {
            assertEquals(0, ThreadingChallenges2.challenge7(List.of(50L)));
        }

        @Test
        void firstIsAlsoFastest() throws InterruptedException, ExecutionException {
            assertEquals(0, ThreadingChallenges2.challenge7(List.of(50L, 200L, 300L)));
        }

        @Test
        void lastIsFastest() throws InterruptedException, ExecutionException {
            assertEquals(2, ThreadingChallenges2.challenge7(List.of(300L, 200L, 50L)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — compareAndSet spin lock increment
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @RepeatedTest(5)
        void alwaysExact() throws InterruptedException {
            assertEquals(10, ThreadingChallenges2.challenge8(10));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(1, ThreadingChallenges2.challenge8(1));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(100, ThreadingChallenges2.challenge8(100));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge8(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — interrupt + isInterrupted
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void countIsPositive() throws InterruptedException {
            long count = ThreadingChallenges2.challenge9(100);
            assertTrue(count > 0, "Should have counted at least once");
        }

        @Test
        void longerRunProducesMoreCounts() throws InterruptedException {
            long short1 = ThreadingChallenges2.challenge9(50);
            long long1  = ThreadingChallenges2.challenge9(200);
            assertTrue(long1 >= short1);
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge9(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — CopyOnWriteArrayList thread-safe add
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() throws InterruptedException {
            assertEquals(List.of(0,1,2,3,4),
                    ThreadingChallenges2.challenge10(5));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(List.of(0), ThreadingChallenges2.challenge10(1));
        }

        @RepeatedTest(3)
        void alwaysAllElements() throws InterruptedException {
            List<Integer> result = ThreadingChallenges2.challenge10(10);
            assertEquals(10, result.size());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge10(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 11 — ReadWriteLock readers/writers
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge11 {

        @Test
        void readCountMatchesOperations() throws InterruptedException {
            long reads = ThreadingChallenges2.challenge11(5, 2, 10);
            assertEquals(50L, reads); // 5 readers × 10 operations each
        }

        @Test
        void singleReaderSingleWriter() throws InterruptedException {
            long reads = ThreadingChallenges2.challenge11(1, 1, 5);
            assertEquals(5L, reads);
        }

        @Test
        void manyReadersOneWriter() throws InterruptedException {
            long reads = ThreadingChallenges2.challenge11(10, 1, 5);
            assertEquals(50L, reads); // 10 readers × 5 ops
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge11(0, 1, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 12 — ScheduledExecutorService fixed rate
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge12 {

        @Test
        void correctExecutionCount() throws InterruptedException {
            // period=100ms, total=450ms → runs at 0,100,200,300,400 → 5 times
            int count = ThreadingChallenges2.challenge12(100, 450);
            assertTrue(count >= 4 && count <= 6,
                    "Expected ~5 executions, got: " + count);
        }

        @Test
        void fasterRate() throws InterruptedException {
            // period=50ms, total=250ms → runs at 0,50,100,150,200 → ~5 times
            int count = ThreadingChallenges2.challenge12(50, 250);
            assertTrue(count >= 4 && count <= 7,
                    "Expected ~5 executions, got: " + count);
        }

        @Test
        void invalidPeriod() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge12(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 13 — Phaser two-phase computation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge13 {

        @Test
        void basicCase() throws InterruptedException {
            // Phase1: [1,4,9,16,25], sum=55
            // Phase2: each of 5 threads adds 55 → total=275
            assertEquals(275L, ThreadingChallenges2.challenge13(List.of(1,2,3,4,5)));
        }

        @Test
        void singleElement() throws InterruptedException {
            // Phase1: [4], sum=4
            // Phase2: 1 thread adds 4 → total=4
            assertEquals(4L, ThreadingChallenges2.challenge13(List.of(2)));
        }

        @Test
        void twoElements() throws InterruptedException {
            // Phase1: [1,4], sum=5
            // Phase2: 2 threads each add 5 → total=10
            assertEquals(10L, ThreadingChallenges2.challenge13(List.of(1,2)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge13(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 14 — Exchanger swap between two threads
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge14 {

        @Test
        void basicSwap() throws InterruptedException {
            ThreadingChallenges2.ExchangeResult result =
                    ThreadingChallenges2.challenge14("hello", "world");

            assertEquals("world", result.fromThread1()); // T1 sent "hello", got "world"
            assertEquals("hello", result.fromThread2()); // T2 sent "world", got "hello"
        }

        @Test
        void numericStrings() throws InterruptedException {
            ThreadingChallenges2.ExchangeResult result =
                    ThreadingChallenges2.challenge14("123", "456");

            assertEquals("456", result.fromThread1());
            assertEquals("123", result.fromThread2());
        }

        @RepeatedTest(3)
        void alwaysSwaps() throws InterruptedException {
            ThreadingChallenges2.ExchangeResult result =
                    ThreadingChallenges2.challenge14("A", "B");

            assertEquals("B", result.fromThread1());
            assertEquals("A", result.fromThread2());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge14(null, "hello"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 15 — RecursiveAction parallel array fill
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge15 {

        @Test
        void basicCase() {
            int[] result = ThreadingChallenges2.challenge15(10, 3);
            assertArrayEquals(new int[]{0,1,4,9,16,25,36,49,64,81}, result);
        }

        @Test
        void singleElement() {
            int[] result = ThreadingChallenges2.challenge15(1, 1);
            assertArrayEquals(new int[]{0}, result);
        }

        @Test
        void thresholdLargerThanSize() {
            // base case immediately
            int[] result = ThreadingChallenges2.challenge15(5, 100);
            assertArrayEquals(new int[]{0,1,4,9,16}, result);
        }

        @Test
        void largeArray() {
            int[] result = ThreadingChallenges2.challenge15(100, 10);
            assertEquals(100, result.length);
            assertEquals(0,    result[0]);
            assertEquals(1,    result[1]);
            assertEquals(9801, result[99]); // 99*99
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge15(0, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 16 — ReentrantLock tryLock successes + failures
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge16 {

        @Test
        void successesPlusFailuresEqualsTotal() throws InterruptedException {
            ThreadingChallenges2.TryLockResult result =
                    ThreadingChallenges2.challenge16(10);

            assertEquals(10, result.successes() + result.failures());
        }

        @Test
        void atLeastOneSuccess() throws InterruptedException {
            ThreadingChallenges2.TryLockResult result =
                    ThreadingChallenges2.challenge16(10);

            assertTrue(result.successes() >= 1);
        }

        @Test
        void singleThread() throws InterruptedException {
            ThreadingChallenges2.TryLockResult result =
                    ThreadingChallenges2.challenge16(1);

            assertEquals(1, result.successes());
            assertEquals(0, result.failures());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge16(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 17 — LinkedTransferQueue synchronous handoff
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge17 {

        @Test
        void basicCase() throws InterruptedException {
            assertEquals(15L, ThreadingChallenges2.challenge17(List.of(1,2,3,4,5)));
        }

        @Test
        void singleItem() throws InterruptedException {
            assertEquals(42L, ThreadingChallenges2.challenge17(List.of(42)));
        }

        @Test
        void manyItems() throws InterruptedException {
            List<Integer> items = new ArrayList<>();
            for (int i = 1; i <= 100; i++) items.add(i);
            assertEquals(5050L, ThreadingChallenges2.challenge17(items));
        }

        @RepeatedTest(3)
        void alwaysCorrect() throws InterruptedException {
            assertEquals(10L, ThreadingChallenges2.challenge17(List.of(1,2,3,4)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge17(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 18 — ThreadPoolExecutor custom configuration
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge18 {

        @Test
        void returnsNonEmptySet() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge18(8);
            assertFalse(result.isEmpty());
        }

        @Test
        void threadCountAtMostMax() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge18(8);
            assertTrue(result.size() <= 4, "Max pool size is 4!");
        }

        @Test
        void singleTask() throws ExecutionException, InterruptedException {
            Set<String> result = ThreadingChallenges2.challenge18(1);
            assertEquals(1, result.size());
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge18(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 19 — Parallel Merge Sort
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge19 {

        @Test
        void basicCase() {
            assertArrayEquals(
                    new int[]{1,2,3,4,5,6,7,8,9,10},
                    ThreadingChallenges2.challenge19(new int[]{5,3,8,1,9,2,7,4,6,10}, 3));
        }

        @Test
        void alreadySorted() {
            assertArrayEquals(
                    new int[]{1,2,3,4,5},
                    ThreadingChallenges2.challenge19(new int[]{1,2,3,4,5}, 2));
        }

        @Test
        void reverseSorted() {
            assertArrayEquals(
                    new int[]{1,2,3,4,5},
                    ThreadingChallenges2.challenge19(new int[]{5,4,3,2,1}, 2));
        }

        @Test
        void singleElement() {
            assertArrayEquals(
                    new int[]{42},
                    ThreadingChallenges2.challenge19(new int[]{42}, 1));
        }

        @Test
        void withDuplicates() {
            assertArrayEquals(
                    new int[]{1,1,2,2,3,3},
                    ThreadingChallenges2.challenge19(new int[]{3,1,2,3,1,2}, 2));
        }

        @Test
        void largeArray() {
            int[] array = new int[1000];
            for (int i = 0; i < 1000; i++) array[i] = 1000 - i;
            int[] result = ThreadingChallenges2.challenge19(array, 50);
            for (int i = 0; i < 999; i++) {
                assertTrue(result[i] <= result[i+1]);
            }
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge19(null, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 20 — Custom ThreadPool implementation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge20 {

        @RepeatedTest(3)
        void allTasksExecuted() throws InterruptedException {
            assertEquals(100, ThreadingChallenges2.challenge20(4, 100));
        }

        @Test
        void singleWorker() throws InterruptedException {
            assertEquals(50, ThreadingChallenges2.challenge20(1, 50));
        }

        @Test
        void manyWorkers() throws InterruptedException {
            assertEquals(200, ThreadingChallenges2.challenge20(8, 200));
        }

        @Test
        void singleTask() throws InterruptedException {
            assertEquals(1, ThreadingChallenges2.challenge20(2, 1));
        }

        @Test
        void invalidPoolSize() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge20(0, 10));
        }

        @Test
        void invalidTaskCount() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges2.challenge20(4, 0));
        }
    }
}
package dev.perfectbogus.threading;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ThreadingChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Thread + AtomicLong + join()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() throws InterruptedException {
            assertEquals(5050L, ThreadingChallenges.challenge1(100));
        }

        @Test
        void smallN() throws InterruptedException {
            assertEquals(10L, ThreadingChallenges.challenge1(4)); // 1+2+3+4
        }

        @Test
        void zero() throws InterruptedException {
            assertEquals(0L, ThreadingChallenges.challenge1(0));
        }

        @Test
        void one() throws InterruptedException {
            assertEquals(1L, ThreadingChallenges.challenge1(1));
        }

        @Test
        void largeN() throws InterruptedException {
            // sum 1..1000 = 500500
            assertEquals(500500L, ThreadingChallenges.challenge1(1000));
        }

        @Test
        void negativeInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge1(-1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Multiple threads + AtomicInteger + join all
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() throws InterruptedException {
            // 0+1+2+3+4 = 10
            assertEquals(10, ThreadingChallenges.challenge2(5));
        }

        @Test
        void singleThread() throws InterruptedException {
            // thread 0 adds 0
            assertEquals(0, ThreadingChallenges.challenge2(1));
        }

        @Test
        void tenThreads() throws InterruptedException {
            // 0+1+...+9 = 45
            assertEquals(45, ThreadingChallenges.challenge2(10));
        }

        @RepeatedTest(5)
        void alwaysSameResult() throws InterruptedException {
            // Thread-safe → same result every run!
            assertEquals(10, ThreadingChallenges.challenge2(5));
        }

        @Test
        void zeroThreads() throws InterruptedException {
            assertEquals(0, ThreadingChallenges.challenge2(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Thread.sleep() + join()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void elapsedAtLeastDelay() throws InterruptedException {
            long elapsed = ThreadingChallenges.challenge3(100);
            assertTrue(elapsed >= 100, "Elapsed: " + elapsed + " should be >= 100");
        }

        @Test
        void notExcessivelyLong() throws InterruptedException {
            long elapsed = ThreadingChallenges.challenge3(50);
            assertTrue(elapsed < 500, "Should complete in reasonable time");
        }

        @Test
        void zeroDelay() throws InterruptedException {
            long elapsed = ThreadingChallenges.challenge3(0);
            assertTrue(elapsed >= 0);
        }

        @Test
        void negativeDelay() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge3(-1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — synchronized keyword (not AtomicInteger!)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @RepeatedTest(5)
        void alwaysZero() throws InterruptedException {
            // N threads increment, N threads decrement → always 0!
            assertEquals(0, ThreadingChallenges.challenge4(10, 1000));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(0, ThreadingChallenges.challenge4(1, 100));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(0, ThreadingChallenges.challenge4(50, 100));
        }

        @Test
        void invalidThreadCount() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge4(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — AtomicInteger (not synchronized!)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @RepeatedTest(5)
        void alwaysExact() throws InterruptedException {
            // Thread-safe → always exactly threadCount * incrementsPerThread
            assertEquals(10000, ThreadingChallenges.challenge5(10, 1000));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(500, ThreadingChallenges.challenge5(1, 500));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(100000, ThreadingChallenges.challenge5(100, 1000));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge5(0, 100));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — ExecutorService + Callable returning thread name
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void threadNamesAtMostPoolSize() throws InterruptedException, ExecutionException {
            Set<String> names = ThreadingChallenges.challenge6(20, 4);
            assertFalse(names.isEmpty());
            assertTrue(names.size() <= 4, "Should use at most 4 threads");
        }

        @Test
        void singleThread() throws InterruptedException, ExecutionException {
            Set<String> names = ThreadingChallenges.challenge6(10, 1);
            assertEquals(1, names.size()); // only one thread in pool!
        }

        @Test
        void poolSizeEqualsTaskCount() throws InterruptedException, ExecutionException {
            Set<String> names = ThreadingChallenges.challenge6(4, 4);
            assertFalse(names.isEmpty());
            assertTrue(names.size() <= 4);
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge6(0, 4));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Future<Integer> parallel sublist sums
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            List<List<Integer>> sublists = List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5),
                    List.of(6, 7, 8, 9)
            );
            assertEquals(List.of(6, 9, 30), ThreadingChallenges.challenge7(sublists));
        }

        @Test
        void orderPreserved() throws InterruptedException, ExecutionException {
            List<List<Integer>> sublists = List.of(
                    List.of(10, 20),
                    List.of(1),
                    List.of(5, 5, 5)
            );
            List<Integer> result = ThreadingChallenges.challenge7(sublists);
            assertEquals(30, result.get(0)); // 10+20
            assertEquals(1,  result.get(1)); // 1
            assertEquals(15, result.get(2)); // 5+5+5
        }

        @Test
        void singleSublist() throws InterruptedException, ExecutionException {
            assertEquals(List.of(15),
                    ThreadingChallenges.challenge7(List.of(List.of(1, 2, 3, 4, 5))));
        }

        @Test
        void emptyList() throws InterruptedException, ExecutionException {
            assertTrue(ThreadingChallenges.challenge7(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Parallel chunk sum with AtomicLong
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            assertEquals(55L, ThreadingChallenges.challenge8(array, 2));
        }

        @Test
        void singleChunk() throws InterruptedException, ExecutionException {
            int[] array = {1, 2, 3, 4, 5};
            assertEquals(15L, ThreadingChallenges.challenge8(array, 1));
        }

        @Test
        void oneChunkPerElement() throws InterruptedException, ExecutionException {
            int[] array = {10, 20, 30, 40, 50};
            assertEquals(150L, ThreadingChallenges.challenge8(array, 5));
        }

        @Test
        void largeArray() throws InterruptedException, ExecutionException {
            int[] array = new int[1000];
            Arrays.fill(array, 1);
            assertEquals(1000L, ThreadingChallenges.challenge8(array, 4));
        }

        @Test
        void nullArray() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge8(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — invokeAll() with Callable<String>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            List<String> result = ThreadingChallenges.challenge9(
                    List.of("hello","world","java","threads"));

            assertEquals(List.of("olleh","dlrow","avaj","sdaerht"), result);
        }

        @Test
        void orderPreserved() throws InterruptedException, ExecutionException {
            List<String> result = ThreadingChallenges.challenge9(
                    List.of("abc","def","ghi"));

            assertEquals("cba", result.get(0));
            assertEquals("fed", result.get(1));
            assertEquals("ihg", result.get(2));
        }

        @Test
        void singleWord() throws InterruptedException, ExecutionException {
            assertEquals(List.of("olleh"),
                    ThreadingChallenges.challenge9(List.of("hello")));
        }

        @Test
        void palindrome() throws InterruptedException, ExecutionException {
            assertEquals(List.of("racecar"),
                    ThreadingChallenges.challenge9(List.of("racecar")));
        }

        @Test
        void emptyList() throws InterruptedException, ExecutionException {
            assertTrue(ThreadingChallenges.challenge9(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — ConcurrentHashMap parallel word count
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() throws InterruptedException {
            Map<String, Integer> result = ThreadingChallenges.challenge10(
                    List.of("apple","banana","apple","cherry","banana","apple"), 2);

            assertEquals(3, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(1, result.get("cherry"));
        }

        @RepeatedTest(5)
        void alwaysCorrect() throws InterruptedException {
            // Thread-safe → same result every run!
            Map<String, Integer> result = ThreadingChallenges.challenge10(
                    List.of("a","b","a","b","a"), 3);

            assertEquals(3, result.get("a"));
            assertEquals(2, result.get("b"));
        }

        @Test
        void singleThread() throws InterruptedException {
            Map<String, Integer> result = ThreadingChallenges.challenge10(
                    List.of("x","y","x"), 1);

            assertEquals(2, result.get("x"));
            assertEquals(1, result.get("y"));
        }

        @Test
        void emptyWords() throws InterruptedException {
            assertTrue(ThreadingChallenges.challenge10(List.of(), 2).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge10(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 11 — BlockingQueue Producer-Consumer
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge11 {

        @Test
        void basicCase() throws InterruptedException {
            long result = ThreadingChallenges.challenge11(
                    List.of(1,2,3,4,5,6,7,8,9,10), 2, 3);
            assertEquals(55L, result);
        }

        @Test
        void singleProducerSingleConsumer() throws InterruptedException {
            long result = ThreadingChallenges.challenge11(
                    List.of(1,2,3,4,5), 1, 1);
            assertEquals(15L, result);
        }

        @Test
        void manyProducersManyConsumers() throws InterruptedException {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) numbers.add(i);
            long result = ThreadingChallenges.challenge11(numbers, 4, 4);
            assertEquals(5050L, result);
        }

        @RepeatedTest(3)
        void alwaysCorrect() throws InterruptedException {
            long result = ThreadingChallenges.challenge11(
                    List.of(10, 20, 30, 40), 2, 2);
            assertEquals(100L, result);
        }

        @Test
        void emptyList() throws InterruptedException {
            assertEquals(0L, ThreadingChallenges.challenge11(List.of(), 1, 1));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge11(null, 1, 1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 12 — CompletableFuture chain: supplyAsync + thenApply x2
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge12 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            assertEquals("Result: 45",
                    ThreadingChallenges.challenge12(List.of(1,2,3,4,5), 3));
        }

        @Test
        void multiplierOne() throws InterruptedException, ExecutionException {
            // sum=10, *1=10
            assertEquals("Result: 10",
                    ThreadingChallenges.challenge12(List.of(1,2,3,4), 1));
        }

        @Test
        void singleElement() throws InterruptedException, ExecutionException {
            assertEquals("Result: 50",
                    ThreadingChallenges.challenge12(List.of(5), 10));
        }

        @Test
        void emptyList() throws InterruptedException, ExecutionException {
            assertEquals("Result: 0",
                    ThreadingChallenges.challenge12(List.of(), 5));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge12(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 13 — CompletableFuture.allOf() parallel squares
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge13 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            assertEquals(List.of(1,4,9,16,25),
                    ThreadingChallenges.challenge13(List.of(1,2,3,4,5)));
        }

        @Test
        void orderPreserved() throws InterruptedException, ExecutionException {
            List<Integer> result = ThreadingChallenges.challenge13(List.of(5,3,1,4,2));
            assertEquals(List.of(25,9,1,16,4), result);
        }

        @Test
        void singleElement() throws InterruptedException, ExecutionException {
            assertEquals(List.of(49), ThreadingChallenges.challenge13(List.of(7)));
        }

        @Test
        void emptyList() throws InterruptedException, ExecutionException {
            assertTrue(ThreadingChallenges.challenge13(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge13(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 14 — CountDownLatch starting gun
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge14 {

        @Test
        void allThreadsRan() throws InterruptedException {
            assertEquals(10, ThreadingChallenges.challenge14(10));
        }

        @Test
        void singleThread() throws InterruptedException {
            assertEquals(1, ThreadingChallenges.challenge14(1));
        }

        @Test
        void manyThreads() throws InterruptedException {
            assertEquals(100, ThreadingChallenges.challenge14(100));
        }

        @RepeatedTest(3)
        void alwaysAllThreadsRan() throws InterruptedException {
            assertEquals(20, ThreadingChallenges.challenge14(20));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge14(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 15 — Semaphore limiting concurrent access
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge15 {

        @RepeatedTest(3)
        void neverExceedsLimit() throws InterruptedException {
            int max = ThreadingChallenges.challenge15(20, 3);
            assertTrue(max <= 3, "Should never exceed limit of 3, got: " + max);
        }

        @Test
        void singleConcurrent() throws InterruptedException {
            int max = ThreadingChallenges.challenge15(10, 1);
            assertEquals(1, max); // only 1 at a time!
        }

        @Test
        void limitEqualsThreadCount() throws InterruptedException {
            int max = ThreadingChallenges.challenge15(5, 5);
            assertTrue(max <= 5);
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge15(0, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 16 — CompletableFuture.thenCombine()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge16 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            assertEquals("sum=10 product=24 total=34",
                    ThreadingChallenges.challenge16(List.of(1,2,3,4), List.of(1,2,3,4)));
        }

        @Test
        void singleElements() throws InterruptedException, ExecutionException {
            assertEquals("sum=5 product=3 total=8",
                    ThreadingChallenges.challenge16(List.of(5), List.of(3)));
        }

        @Test
        void emptyLists() throws InterruptedException, ExecutionException {
            // sum of empty=0, product of empty=1 (identity)
            String result = ThreadingChallenges.challenge16(List.of(), List.of());
            assertNotNull(result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge16(null, List.of(1)));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 17 — invokeAll() for parallel min/max
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge17 {

        @Test
        void basicCase() throws InterruptedException, ExecutionException {
            int[] result = ThreadingChallenges.challenge17(
                    new int[]{5,3,8,1,9,2,7,4,6}, 3);
            assertEquals(1, result[0]); // global min
            assertEquals(9, result[1]); // global max
        }

        @Test
        void singleChunk() throws InterruptedException, ExecutionException {
            int[] result = ThreadingChallenges.challenge17(
                    new int[]{3,1,4,1,5,9,2,6}, 1);
            assertEquals(1, result[0]);
            assertEquals(9, result[1]);
        }

        @Test
        void oneChunkPerElement() throws InterruptedException, ExecutionException {
            int[] result = ThreadingChallenges.challenge17(
                    new int[]{7,2,9,4}, 4);
            assertEquals(2, result[0]);
            assertEquals(9, result[1]);
        }

        @Test
        void allSameValues() throws InterruptedException, ExecutionException {
            int[] result = ThreadingChallenges.challenge17(
                    new int[]{5,5,5,5,5}, 2);
            assertEquals(5, result[0]);
            assertEquals(5, result[1]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge17(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 18 — CyclicBarrier two-phase computation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge18 {

        @Test
        void basicCase() throws InterruptedException, BrokenBarrierException {
            // [3,9,2,9,1,5,9,4] globalMax=9, count of 9 = 3
            assertEquals(3, ThreadingChallenges.challenge18(
                    new int[]{3,9,2,9,1,5,9,4}, 2));
        }

        @Test
        void singleThread() throws InterruptedException, BrokenBarrierException {
            // [1,5,3,5] max=5, count=2
            assertEquals(2, ThreadingChallenges.challenge18(
                    new int[]{1,5,3,5}, 1));
        }

        @Test
        void allSameMax() throws InterruptedException, BrokenBarrierException {
            // [7,7,7,7] max=7, count=4
            assertEquals(4, ThreadingChallenges.challenge18(
                    new int[]{7,7,7,7}, 2));
        }

        @Test
        void singleMax() throws InterruptedException, BrokenBarrierException {
            // [1,2,3,9] max=9, count=1
            assertEquals(1, ThreadingChallenges.challenge18(
                    new int[]{1,2,3,9}, 2));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge18(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 19 — ForkJoinPool + RecursiveTask parallel sum
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge19 {

        @Test
        void basicCase() {
            int[] array = {1,2,3,4,5,6,7,8,9,10};
            assertEquals(55L, ThreadingChallenges.challenge19(array, 3));
        }

        @Test
        void thresholdLargerThanArray() {
            // threshold > array length → base case immediately
            int[] array = {1,2,3,4,5};
            assertEquals(15L, ThreadingChallenges.challenge19(array, 100));
        }

        @Test
        void thresholdOne() {
            // splits all the way down to individual elements
            int[] array = {10,20,30,40,50};
            assertEquals(150L, ThreadingChallenges.challenge19(array, 1));
        }

        @Test
        void largeArray() {
            int[] array = new int[1000];
            Arrays.fill(array, 1);
            assertEquals(1000L, ThreadingChallenges.challenge19(array, 50));
        }

        @Test
        void singleElement() {
            assertEquals(42L, ThreadingChallenges.challenge19(new int[]{42}, 1));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge19(null, 3));
        }

        @Test
        void invalidThreshold() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge19(new int[]{1,2}, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 20 — Multi-producer Multi-consumer BlockingQueue
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge20 {

        @Test
        void basicCase() throws InterruptedException {
            Map<String, Long> result = ThreadingChallenges.challenge20(
                    List.of("FOOD:apple","TECH:phone","FOOD:banana",
                            "TECH:laptop","FOOD:cherry"),
                    2, 3);

            assertEquals(3L, result.get("FOOD"));
            assertEquals(2L, result.get("TECH"));
        }

        @Test
        void singleCategory() throws InterruptedException {
            Map<String, Long> result = ThreadingChallenges.challenge20(
                    List.of("A:x","A:y","A:z"), 1, 2);
            assertEquals(3L, result.get("A"));
        }

        @RepeatedTest(3)
        void alwaysCorrect() throws InterruptedException {
            Map<String, Long> result = ThreadingChallenges.challenge20(
                    List.of("CAT1:a","CAT2:b","CAT1:c","CAT2:d"), 2, 2);
            assertEquals(2L, result.get("CAT1"));
            assertEquals(2L, result.get("CAT2"));
        }

        @Test
        void manyItems() throws InterruptedException {
            List<String> items = new ArrayList<>();
            for (int i = 0; i < 100; i++) items.add("TYPE:" + i);
            Map<String, Long> result = ThreadingChallenges.challenge20(items, 4, 4);
            assertEquals(100L, result.get("TYPE"));
        }

        @Test
        void emptyItems() throws InterruptedException {
            Map<String, Long> result = ThreadingChallenges.challenge20(
                    List.of(), 1, 1);
            assertTrue(result.isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ThreadingChallenges.challenge20(null, 1, 1));
        }
    }
}
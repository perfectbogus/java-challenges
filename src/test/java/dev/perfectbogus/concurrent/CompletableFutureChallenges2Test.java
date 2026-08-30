package dev.perfectbogus.concurrent;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — supplyAsync + thenRun() side effect
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void thenRunFiresOnce() throws ExecutionException, InterruptedException {
            assertEquals(1, CompletableFutureChallenges2.challenge1(List.of(1,2,3,4,5)));
        }

        @Test
        void thenRunFiresOnceForEmptyList() throws ExecutionException, InterruptedException {
            assertEquals(1, CompletableFutureChallenges2.challenge1(List.of()));
        }

        @Test
        void thenRunFiresOnceForSingleElement() throws ExecutionException, InterruptedException {
            assertEquals(1, CompletableFutureChallenges2.challenge1(List.of(42)));
        }

        @RepeatedTest(3)
        void thenRunAlwaysFiresOnce() throws ExecutionException, InterruptedException {
            assertEquals(1, CompletableFutureChallenges2.challenge1(List.of(1,2,3)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — whenComplete() observing both success and failure
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void successCase() throws InterruptedException {
            assertEquals("SUCCESS:5", CompletableFutureChallenges2.challenge2(10, 2));
        }

        @Test
        void divisionByZero() throws InterruptedException {
            assertEquals("FAILURE:/ by zero",
                    CompletableFutureChallenges2.challenge2(10, 0));
        }

        @Test
        void zeroDividend() throws InterruptedException {
            assertEquals("SUCCESS:0", CompletableFutureChallenges2.challenge2(0, 5));
        }

        @Test
        void resultIsOne() throws InterruptedException {
            assertEquals("SUCCESS:1", CompletableFutureChallenges2.challenge2(5, 5));
        }

        @RepeatedTest(3)
        void alwaysFailsOnZeroDivisor() throws InterruptedException {
            String result = CompletableFutureChallenges2.challenge2(42, 0);
            assertTrue(result.startsWith("FAILURE:"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — completedFuture + 3 thenApply chain
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals("Result: 49", CompletableFutureChallenges2.challenge3(7));
        }

        @Test
        void squareOfOne() throws ExecutionException, InterruptedException {
            assertEquals("Result: 1", CompletableFutureChallenges2.challenge3(1));
        }

        @Test
        void squareOfZero() throws ExecutionException, InterruptedException {
            assertEquals("Result: 0", CompletableFutureChallenges2.challenge3(0));
        }

        @Test
        void squareOfTen() throws ExecutionException, InterruptedException {
            assertEquals("Result: 100", CompletableFutureChallenges2.challenge3(10));
        }

        @Test
        void squareOfFive() throws ExecutionException, InterruptedException {
            assertEquals("Result: 25", CompletableFutureChallenges2.challenge3(5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — supplyAsync with custom ExecutorService
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void threadNamesAtMostPoolSize() throws ExecutionException, InterruptedException {
            Set<String> names = CompletableFutureChallenges2.challenge4(20, 4);
            assertFalse(names.isEmpty());
            assertTrue(names.size() <= 4, "Should use at most 4 threads!");
        }

        @Test
        void singleThreadPool() throws ExecutionException, InterruptedException {
            Set<String> names = CompletableFutureChallenges2.challenge4(10, 1);
            assertEquals(1, names.size()); // only one thread!
        }

        @Test
        void poolSizeEqualsTaskCount() throws ExecutionException, InterruptedException {
            Set<String> names = CompletableFutureChallenges2.challenge4(3, 3);
            assertFalse(names.isEmpty());
            assertTrue(names.size() <= 3);
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge4(0, 4));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — thenAcceptBoth() to consume two parallel results
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals("longest=strawberry shortest=pear",
                    CompletableFutureChallenges2.challenge5(
                            List.of("apple","kiwi","strawberry","fig"),
                            List.of("banana","mango","pear","blueberry")));
        }

        @Test
        void singleElementLists() throws ExecutionException, InterruptedException {
            assertEquals("longest=hello shortest=hi",
                    CompletableFutureChallenges2.challenge5(
                            List.of("hello"), List.of("hi")));
        }

        @Test
        void sameLengthWords() throws ExecutionException, InterruptedException {
            String result = CompletableFutureChallenges2.challenge5(
                    List.of("cat","dog","ant"),
                    List.of("bee","fly","ant"));
            assertTrue(result.startsWith("longest="));
            assertTrue(result.contains("shortest="));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge5(null, List.of("a")));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — runAsync + thenApply chain
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges2.RunResult result =
                    CompletableFutureChallenges2.challenge6(List.of(1,2,3,4,5), 2);
            assertEquals(1,  result.counterValue());  // runAsync fired once
            assertEquals(30, result.computedValue()); // 15*2=30
        }

        @Test
        void multiplierOne() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges2.RunResult result =
                    CompletableFutureChallenges2.challenge6(List.of(1,2,3), 1);
            assertEquals(1, result.counterValue());
            assertEquals(6, result.computedValue()); // 1+2+3=6, *1=6
        }

        @Test
        void emptyList() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges2.RunResult result =
                    CompletableFutureChallenges2.challenge6(List.of(), 5);
            assertEquals(1, result.counterValue()); // runAsync still fires!
            assertEquals(0, result.computedValue());
        }

        @RepeatedTest(3)
        void counterAlwaysOne() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges2.RunResult result =
                    CompletableFutureChallenges2.challenge6(List.of(1,2,3), 2);
            assertEquals(1, result.counterValue()); // thenRun fires exactly once!
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge6(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — allOf + combine 3 futures into record
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            Map<Integer, String> names    = Map.of(1, "Alice", 2, "Bob");
            Map<Integer, String> depts    = Map.of(1, "Engineering", 2, "Marketing");
            Map<Integer, Double> salaries = Map.of(1, 95000.0, 2, 60000.0);

            CompletableFutureChallenges2.EmployeeSummary result =
                    CompletableFutureChallenges2.challenge7(1, names, depts, salaries);

            assertEquals("Alice",       result.name());
            assertEquals("Engineering", result.department());
            assertEquals(95000.0,       result.salary(), 0.01);
        }

        @Test
        void secondEmployee() throws ExecutionException, InterruptedException {
            Map<Integer, String> names    = Map.of(1, "Alice", 2, "Bob");
            Map<Integer, String> depts    = Map.of(1, "Engineering", 2, "Marketing");
            Map<Integer, Double> salaries = Map.of(1, 95000.0, 2, 60000.0);

            CompletableFutureChallenges2.EmployeeSummary result =
                    CompletableFutureChallenges2.challenge7(2, names, depts, salaries);

            assertEquals("Bob",       result.name());
            assertEquals("Marketing", result.department());
            assertEquals(60000.0,     result.salary(), 0.01);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge7(1, null, Map.of(), Map.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — exceptionally() with COMPUTED fallback
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void validInteger() throws ExecutionException, InterruptedException {
            assertEquals(123, CompletableFutureChallenges2.challenge8("123"));
        }

        @Test
        void invalidStringFallsBackToLength() throws ExecutionException, InterruptedException {
            assertEquals(5, CompletableFutureChallenges2.challenge8("hello")); // len=5
        }

        @Test
        void shortInvalidString() throws ExecutionException, InterruptedException {
            assertEquals(3, CompletableFutureChallenges2.challenge8("abc")); // len=3
        }

        @Test
        void zeroString() throws ExecutionException, InterruptedException {
            assertEquals(0, CompletableFutureChallenges2.challenge8("0"));
        }

        @Test
        void negativeInteger() throws ExecutionException, InterruptedException {
            assertEquals(-42, CompletableFutureChallenges2.challenge8("-42"));
        }

        @Test
        void emptyStringFallsBackToLength() throws ExecutionException, InterruptedException {
            assertEquals(0, CompletableFutureChallenges2.challenge8("")); // len=0
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — completeOnTimeout() for timeout handling
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void completesBeforeTimeout() throws ExecutionException, InterruptedException {
            // sleepMs=50 < timeoutMs=200 → actual value returned
            assertEquals(42, CompletableFutureChallenges2.challenge9(42, 50, 200, 0));
        }

        @Test
        void timesOut() throws ExecutionException, InterruptedException {
            // sleepMs=500 > timeoutMs=100 → default returned
            assertEquals(0, CompletableFutureChallenges2.challenge9(42, 500, 100, 0));
        }

        @Test
        void customDefaultValue() throws ExecutionException, InterruptedException {
            // times out → returns -1 as default
            assertEquals(-1, CompletableFutureChallenges2.challenge9(42, 500, 50, -1));
        }

        @Test
        void zeroSleepAlwaysCompletes() throws ExecutionException, InterruptedException {
            // sleepMs=0 → always completes before any timeout
            assertEquals(99, CompletableFutureChallenges2.challenge9(99, 0, 100, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — 3-step thenCompose chain
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private Map<Integer, String> userMap;
        private Map<String, String>  deptMap;
        private Map<String, Double>  budgetMap;

        @org.junit.jupiter.api.BeforeEach
        void setUp() {
            userMap   = Map.of(1, "alice",   2, "bob",       3, "carol");
            deptMap   = Map.of("alice", "Engineering", "bob", "Marketing", "carol", "HR");
            budgetMap = Map.of("Engineering", 500000.0, "Marketing", 200000.0, "HR", 100000.0);
        }

        @Test
        void engineeringBudget() throws ExecutionException, InterruptedException {
            assertEquals(500000.0,
                    CompletableFutureChallenges2.challenge10(1, userMap, deptMap, budgetMap), 0.01);
        }

        @Test
        void marketingBudget() throws ExecutionException, InterruptedException {
            assertEquals(200000.0,
                    CompletableFutureChallenges2.challenge10(2, userMap, deptMap, budgetMap), 0.01);
        }

        @Test
        void hrBudget() throws ExecutionException, InterruptedException {
            assertEquals(100000.0,
                    CompletableFutureChallenges2.challenge10(3, userMap, deptMap, budgetMap), 0.01);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges2.challenge10(1, null, deptMap, budgetMap));
        }
    }
}
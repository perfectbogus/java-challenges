package dev.perfectbogus.concurrent;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — supplyAsync + get() to compute sum
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals(15, CompletableFutureChallenges.challenge1(List.of(1,2,3,4,5)));
        }

        @Test
        void singleElement() throws ExecutionException, InterruptedException {
            assertEquals(42, CompletableFutureChallenges.challenge1(List.of(42)));
        }

        @Test
        void emptyList() throws ExecutionException, InterruptedException {
            assertEquals(0, CompletableFutureChallenges.challenge1(List.of()));
        }

        @Test
        void withNegatives() throws ExecutionException, InterruptedException {
            assertEquals(0, CompletableFutureChallenges.challenge1(List.of(-5,0,5)));
        }

        @Test
        void largeList() throws ExecutionException, InterruptedException {
            // sum 1..100 = 5050
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) numbers.add(i);
            assertEquals(5050, CompletableFutureChallenges.challenge1(numbers));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — supplyAsync + two thenApply() chain
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals("<<HELLO WORLD>>",
                    CompletableFutureChallenges.challenge2("  hello world  "));
        }

        @Test
        void noWhitespace() throws ExecutionException, InterruptedException {
            assertEquals("<<JAVA>>",
                    CompletableFutureChallenges.challenge2("java"));
        }

        @Test
        void alreadyUppercase() throws ExecutionException, InterruptedException {
            assertEquals("<<HELLO>>",
                    CompletableFutureChallenges.challenge2("HELLO"));
        }

        @Test
        void onlySpaces() throws ExecutionException, InterruptedException {
            assertEquals("<<>>",
                    CompletableFutureChallenges.challenge2("   "));
        }

        @Test
        void emptyString() throws ExecutionException, InterruptedException {
            assertEquals("<<>>",
                    CompletableFutureChallenges.challenge2(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — supplyAsync + thenAccept → AtomicInteger
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals(3,
                    CompletableFutureChallenges.challenge3(List.of(1,5,8,3,9,2,7), 5));
        }

        @Test
        void noneAboveThreshold() throws ExecutionException, InterruptedException {
            assertEquals(0,
                    CompletableFutureChallenges.challenge3(List.of(1,2,3), 10));
        }

        @Test
        void allAboveThreshold() throws ExecutionException, InterruptedException {
            assertEquals(4,
                    CompletableFutureChallenges.challenge3(List.of(6,7,8,9), 5));
        }

        @Test
        void exactlyAtThresholdNotCounted() throws ExecutionException, InterruptedException {
            // threshold=5 → 5 is NOT > 5
            assertEquals(0,
                    CompletableFutureChallenges.challenge3(List.of(5), 5));
        }

        @Test
        void emptyList() throws ExecutionException, InterruptedException {
            assertEquals(0,
                    CompletableFutureChallenges.challenge3(List.of(), 5));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge3(null, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — supplyAsync + thenApply → format max salary
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Employee> employees = List.of(
                    new CompletableFutureChallenges.Employee("Alice", 95000),
                    new CompletableFutureChallenges.Employee("Bob",   60000),
                    new CompletableFutureChallenges.Employee("Carol", 85000)
            );
            assertEquals("Max salary: 95000.00",
                    CompletableFutureChallenges.challenge4(employees));
        }

        @Test
        void singleEmployee() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Employee> employees = List.of(
                    new CompletableFutureChallenges.Employee("Alice", 80000));
            assertEquals("Max salary: 80000.00",
                    CompletableFutureChallenges.challenge4(employees));
        }

        @Test
        void allSameSalary() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Employee> employees = List.of(
                    new CompletableFutureChallenges.Employee("Alice", 70000),
                    new CompletableFutureChallenges.Employee("Bob",   70000)
            );
            assertEquals("Max salary: 70000.00",
                    CompletableFutureChallenges.challenge4(employees));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge4(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge4(List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — supplyAsync + thenCompose() chaining
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        private Map<Integer, String> deptNames;
        private Map<String, Integer> deptCounts;

        @org.junit.jupiter.api.BeforeEach
        void setUp() {
            deptNames  = Map.of(1, "Engineering", 2, "Marketing", 3, "HR");
            deptCounts = Map.of("Engineering", 15, "Marketing", 8, "HR", 3);
        }

        @Test
        void engineeringDept() throws ExecutionException, InterruptedException {
            assertEquals(15, CompletableFutureChallenges.challenge5(1, deptNames, deptCounts));
        }

        @Test
        void marketingDept() throws ExecutionException, InterruptedException {
            assertEquals(8, CompletableFutureChallenges.challenge5(2, deptNames, deptCounts));
        }

        @Test
        void hrDept() throws ExecutionException, InterruptedException {
            assertEquals(3, CompletableFutureChallenges.challenge5(3, deptNames, deptCounts));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge5(1, null, deptCounts));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — thenCombine() to merge two parallel futures
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges.CombineResult result =
                    CompletableFutureChallenges.challenge6(
                            List.of(1,2,3,4), List.of(1,2,3,4));

            assertEquals(10, result.sum());     // 1+2+3+4
            assertEquals(24, result.product()); // 1*2*3*4
        }

        @Test
        void singleElements() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges.CombineResult result =
                    CompletableFutureChallenges.challenge6(List.of(5), List.of(3));

            assertEquals(5, result.sum());
            assertEquals(3, result.product());
        }

        @Test
        void sumIsZero() throws ExecutionException, InterruptedException {
            CompletableFutureChallenges.CombineResult result =
                    CompletableFutureChallenges.challenge6(
                            List.of(-5,5), List.of(2,3));

            assertEquals(0, result.sum());
            assertEquals(6, result.product());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge6(null, List.of(1)));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — allOf() to square numbers in parallel
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() throws ExecutionException, InterruptedException {
            assertEquals(List.of(1,4,9,16,25),
                    CompletableFutureChallenges.challenge7(List.of(1,2,3,4,5)));
        }

        @Test
        void orderPreserved() throws ExecutionException, InterruptedException {
            List<Integer> result = CompletableFutureChallenges.challenge7(
                    List.of(5,3,1,4,2));
            assertEquals(List.of(25,9,1,16,4), result);
        }

        @Test
        void singleElement() throws ExecutionException, InterruptedException {
            assertEquals(List.of(49),
                    CompletableFutureChallenges.challenge7(List.of(7)));
        }

        @Test
        void withZero() throws ExecutionException, InterruptedException {
            assertEquals(List.of(0,1,4),
                    CompletableFutureChallenges.challenge7(List.of(0,1,2)));
        }

        @Test
        void emptyList() throws ExecutionException, InterruptedException {
            assertTrue(CompletableFutureChallenges.challenge7(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — exceptionally() for safe division
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void normalDivision() throws ExecutionException, InterruptedException {
            assertEquals(5, CompletableFutureChallenges.challenge8(10, 2));
        }

        @Test
        void divisionByZero() throws ExecutionException, InterruptedException {
            assertEquals(-1, CompletableFutureChallenges.challenge8(10, 0));
        }

        @Test
        void dividendIsZero() throws ExecutionException, InterruptedException {
            assertEquals(0, CompletableFutureChallenges.challenge8(0, 5));
        }

        @Test
        void resultIsOne() throws ExecutionException, InterruptedException {
            assertEquals(1, CompletableFutureChallenges.challenge8(5, 5));
        }

        @Test
        void largeNumbers() throws ExecutionException, InterruptedException {
            assertEquals(100, CompletableFutureChallenges.challenge8(1000, 10));
        }

        @RepeatedTest(3)
        void alwaysReturnsNegativeOneOnZero()
                throws ExecutionException, InterruptedException {
            assertEquals(-1, CompletableFutureChallenges.challenge8(42, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — handle() for both success and failure cases
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void successCase() throws ExecutionException, InterruptedException {
            assertEquals("olleh",
                    CompletableFutureChallenges.challenge9("hello"));
        }

        @Test
        void emptyStringFailure() throws ExecutionException, InterruptedException {
            assertEquals("ERROR: String cannot be empty",
                    CompletableFutureChallenges.challenge9(""));
        }

        @Test
        void singleChar() throws ExecutionException, InterruptedException {
            assertEquals("a", CompletableFutureChallenges.challenge9("a"));
        }

        @Test
        void palindrome() throws ExecutionException, InterruptedException {
            assertEquals("racecar",
                    CompletableFutureChallenges.challenge9("racecar"));
        }

        @Test
        void longerString() throws ExecutionException, InterruptedException {
            assertEquals("avaj",
                    CompletableFutureChallenges.challenge9("java"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — anyOf() returns first to complete
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void fastestWins() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Task> tasks = List.of(
                    new CompletableFutureChallenges.Task("slow",   300),
                    new CompletableFutureChallenges.Task("fast",    50),
                    new CompletableFutureChallenges.Task("medium", 150)
            );
            assertEquals("fast", CompletableFutureChallenges.challenge10(tasks));
        }

        @Test
        void singleTask() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Task> tasks = List.of(
                    new CompletableFutureChallenges.Task("only", 50));
            assertEquals("only", CompletableFutureChallenges.challenge10(tasks));
        }

        @Test
        void twoTasks() throws ExecutionException, InterruptedException {
            List<CompletableFutureChallenges.Task> tasks = List.of(
                    new CompletableFutureChallenges.Task("second", 200),
                    new CompletableFutureChallenges.Task("first",   50)
            );
            assertEquals("first", CompletableFutureChallenges.challenge10(tasks));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge10(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CompletableFutureChallenges.challenge10(List.of()));
        }
    }
}
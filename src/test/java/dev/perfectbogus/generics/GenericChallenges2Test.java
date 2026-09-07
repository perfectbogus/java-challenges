package dev.perfectbogus.generics;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class GenericChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Generic reverse
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void reverseIntegers() {
            assertEquals(List.of(5,4,3,2,1),
                    GenericChallenges2.challenge1(List.of(1,2,3,4,5)));
        }

        @Test
        void reverseStrings() {
            assertEquals(List.of("c","b","a"),
                    GenericChallenges2.challenge1(List.of("a","b","c")));
        }

        @Test
        void singleElement() {
            assertEquals(List.of(42),
                    GenericChallenges2.challenge1(List.of(42)));
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges2.challenge1(List.of()).isEmpty());
        }

        @Test
        void originalNotModified() {
            List<Integer> original = new ArrayList<>(List.of(1,2,3));
            GenericChallenges2.challenge1(original);
            assertEquals(List.of(1,2,3), original);
        }

        @Test
        void palindrome() {
            assertEquals(List.of(1,2,3,2,1),
                    GenericChallenges2.challenge1(List.of(1,2,3,2,1)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Generic distinct preserving order
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void distinctIntegers() {
            assertEquals(List.of(1,2,3,4),
                    GenericChallenges2.challenge2(List.of(1,2,3,2,1,4)));
        }

        @Test
        void distinctStrings() {
            assertEquals(List.of("a","b","c"),
                    GenericChallenges2.challenge2(List.of("a","b","a","c","b")));
        }

        @Test
        void allDuplicates() {
            assertEquals(List.of(1),
                    GenericChallenges2.challenge2(List.of(1,1,1)));
        }

        @Test
        void noDuplicates() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges2.challenge2(List.of(1,2,3)));
        }

        @Test
        void keepsFirstOccurrence() {
            List<Integer> result =
                    GenericChallenges2.challenge2(List.of(3,1,2,1,3));

            assertEquals(List.of(3,1,2), result); // 3 and 1 first, not second!
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges2.challenge2(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Generic firstOrDefault
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void matchFound() {
            assertEquals(4,
                    GenericChallenges2.challenge3(List.of(1,2,3,4,5), n -> n > 3, 0));
        }

        @Test
        void noMatchReturnsDefault() {
            assertEquals(0,
                    GenericChallenges2.challenge3(List.of(1,2,3), n -> n > 10, 0));
        }

        @Test
        void stringMatch() {
            assertEquals("banana",
                    GenericChallenges2.challenge3(
                            List.of("apple","banana","cherry"),
                            s -> s.startsWith("b"), "none"));
        }

        @Test
        void emptyListReturnsDefault() {
            assertEquals(42,
                    GenericChallenges2.challenge3(List.of(), n -> true, 42));
        }

        @Test
        void firstMatchReturned() {
            // both 4 and 5 match → return 4 (first!)
            assertEquals(4,
                    GenericChallenges2.challenge3(List.of(1,2,3,4,5), n -> n > 3, 0));
        }

        @Test
        void nullList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge3(null, n -> true, 0));
        }

        @Test
        void nullPredicate() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge3(List.of(1), null, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Generic list to Map with key extractor
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void groupByLength() {
            Map<Integer, String> result =
                    GenericChallenges2.challenge4_2(
                            List.of("hi","bye","hey"), String::length);

            assertEquals("hey", result.get(3));
            assertEquals("hi",  result.get(2));
        }

        @Test
        void employeeByName() {
            List<GenericChallenges2.Employee> employees = List.of(
                    new GenericChallenges2.Employee("Alice", 95000),
                    new GenericChallenges2.Employee("Bob",   60000)
            );
            Map<String, GenericChallenges2.Employee> result =
                    GenericChallenges2.challenge4(employees,
                            GenericChallenges2.Employee::name);

            assertEquals(95000.0, result.get("Alice").salary(), 0.01);
            assertEquals(60000.0, result.get("Bob").salary(),   0.01);
        }

        @Test
        void lastValueWinsOnDuplicate() {
            Map<Integer, String> result =
                    GenericChallenges2.challenge4(
                            List.of("cat","dog","ant"), String::length);

            // all length 3 → last wins: "ant"
            assertEquals("ant", result.get(3));
            assertEquals(1, result.size());
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges2.challenge4(
                    List.<String>of(), String::length).isEmpty());
        }

        @Test
        void nullList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge4(null, s -> s));
        }

        @Test
        void nullExtractor() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge4(List.of("a"), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Generic repeat
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void repeatString() {
            assertEquals(List.of("hello","hello","hello"),
                    GenericChallenges2.challenge5("hello", 3));
        }

        @Test
        void repeatInteger() {
            assertEquals(List.of(42,42,42,42,42),
                    GenericChallenges2.challenge5(42, 5));
        }

        @Test
        void repeatZeroTimes() {
            assertTrue(GenericChallenges2.challenge5("x", 0).isEmpty());
        }

        @Test
        void repeatNullValue() {
            List<String> result = GenericChallenges2.challenge5(null, 2);
            assertEquals(2, result.size());
            assertNull(result.get(0));
            assertNull(result.get(1));
        }

        @Test
        void repeatOnce() {
            assertEquals(List.of(7), GenericChallenges2.challenge5(7, 1));
        }

        @Test
        void negativeNThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge5("x", -1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Generic BoundedQueue<T>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicOfferAndPoll() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(3);

            assertTrue(q.offer(1));
            assertTrue(q.offer(2));
            assertTrue(q.offer(3));
            assertEquals(1, q.poll()); // FIFO!
            assertEquals(2, q.poll());
            assertEquals(3, q.poll());
        }

        @Test
        void rejectWhenFull() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(2);

            assertTrue(q.offer(1));
            assertTrue(q.offer(2));
            assertFalse(q.offer(3)); // ← rejected!
            assertEquals(2, q.size());
        }

        @Test
        void peekDoesNotRemove() {
            GenericChallenges2.BoundedQueue<String> q =
                    GenericChallenges2.challenge6(3);

            q.offer("first");
            q.offer("second");
            assertEquals("first", q.peek()); // ← peek
            assertEquals("first", q.peek()); // ← still there!
            assertEquals(2, q.size());
        }

        @Test
        void pollEmptyReturnsNull() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(3);
            assertNull(q.poll());
        }

        @Test
        void peekEmptyReturnsNull() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(3);
            assertNull(q.peek());
        }

        @Test
        void isFullAndIsEmpty() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(2);

            assertTrue(q.isEmpty());
            assertFalse(q.isFull());
            q.offer(1);
            q.offer(2);
            assertTrue(q.isFull());
            assertFalse(q.isEmpty());
        }

        @Test
        void canReuseAfterPoll() {
            GenericChallenges2.BoundedQueue<Integer> q =
                    GenericChallenges2.challenge6(2);

            q.offer(1); q.offer(2);
            q.poll();              // remove 1 → not full anymore
            assertTrue(q.offer(3)); // ← should succeed now!
            assertEquals(2, q.size());
        }

        @Test
        void invalidCapacity() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge6(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Generic transformMap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void salaryRaise() {
            Map<String, Double> input = Map.of("Alice", 95000.0, "Bob", 60000.0);
            Map<String, Double> result =
                    GenericChallenges2.challenge7(input, salary -> salary * 1.1);

            assertEquals(104500.0, result.get("Alice"), 0.01);
            assertEquals(66000.0,  result.get("Bob"),   0.01);
        }

        @Test
        void salaryRaise7_2() {
            Map<String, Double> input = Map.of("Alice", 95000.0, "Bob", 60000.0);
            Map<String, Double> result =
                    GenericChallenges2.challenge7_2(input, salary -> salary * 1.1);

            assertEquals(104500.0, result.get("Alice"), 0.01);
            assertEquals(66000.0,  result.get("Bob"),   0.01);
        }

        @Test
        void squareValues() {
            Map<String, Integer> input = Map.of("a", 1, "b", 2, "c", 3);
            Map<String, Integer> result =
                    GenericChallenges2.challenge7(input, n -> n * n);

            assertEquals(1, result.get("a"));
            assertEquals(4, result.get("b"));
            assertEquals(9, result.get("c"));
        }

        @Test
        void changeValueType() {
            Map<String, Integer> input = Map.of("hello", 5, "hi", 2);
            Map<String, String> result =
                    GenericChallenges2.challenge7(input, n -> "count=" + n);

            assertEquals("count=5", result.get("hello"));
            assertEquals("count=2", result.get("hi"));
        }

        @Test
        void emptyMap() {
            assertTrue(GenericChallenges2.challenge7(
                    Map.<String, Integer>of(), n -> n * 2).isEmpty());
        }

        @Test
        void nullMap() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge7(null, n -> n));
        }

        @Test
        void nullMapper() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge7(Map.of("a", 1), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Generic merge sorted lists
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void mergeIntegers() {
            assertEquals(List.of(1,2,3,4,5,6,7,8),
                    GenericChallenges2.challenge8(
                            List.of(1,3,5,7), List.of(2,4,6,8),
                            Integer::compareTo));
        }

        @Test
        void mergeStrings() {
            assertEquals(List.of("apple","banana","cherry","date"),
                    GenericChallenges2.challenge8(
                            List.of("apple","cherry"), List.of("banana","date"),
                            String::compareTo));
        }

        @Test
        void emptyFirstList() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges2.challenge8(
                            List.of(), List.of(1,2,3), Integer::compareTo));
        }

        @Test
        void emptySecondList() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges2.challenge8(
                            List.of(1,2,3), List.of(), Integer::compareTo));
        }

        @Test
        void bothEmpty() {
            assertTrue(GenericChallenges2.challenge8(
                    List.<Integer>of(), List.<Integer>of(),
                    Integer::compareTo).isEmpty());
        }

        @Test
        void differentLengths() {
            assertEquals(List.of(1,2,3,4,5),
                    GenericChallenges2.challenge8(
                            List.of(1,3,5), List.of(2,4), Integer::compareTo));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge8(
                            null, List.of(1), Integer::compareTo));
        }

        @Test
        void nullComparator() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge8(
                            List.of(1), List.of(2), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Generic Either<L, R>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void leftEither() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9left("error");

            assertTrue(either.isLeft());
            assertFalse(either.isRight());
            assertEquals("error", either.getLeft());
            assertNull(either.getRight());
        }

        @Test
        void rightEither() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9right(42);

            assertFalse(either.isLeft());
            assertTrue(either.isRight());
            assertEquals(42, either.getRight());
            assertNull(either.getLeft());
        }

        @Test
        void mapLeftOnLeft() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9left("hello");
            GenericChallenges2.Either<Integer, Integer> mapped =
                    either.mapLeft(String::length);

            assertTrue(mapped.isLeft());
            assertEquals(5, mapped.getLeft());
        }

        @Test
        void mapLeftOnRightUnchanged() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9right(42);
            GenericChallenges2.Either<Integer, Integer> mapped =
                    either.mapLeft(String::length); // ← never called!

            assertTrue(mapped.isRight());
            assertEquals(42, mapped.getRight());
        }

        @Test
        void mapRightOnRight() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9right(42);
            GenericChallenges2.Either<String, Integer> mapped =
                    either.mapRight(n -> n * 2);

            assertEquals(84, mapped.getRight());
        }

        @Test
        void mapRightOnLeftUnchanged() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9left("error");
            GenericChallenges2.Either<String, Integer> mapped =
                    either.mapRight(n -> n * 2); // ← never called!

            assertTrue(mapped.isLeft());
            assertEquals("error", mapped.getLeft());
        }

        @Test
        void toStringLeft() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9left("error");
            assertEquals("Left(error)", either.toString());
        }

        @Test
        void toStringRight() {
            GenericChallenges2.Either<String, Integer> either =
                    GenericChallenges2.challenge9right(42);
            assertEquals("Right(42)", either.toString());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Generic memoize function
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void computesOnFirstCall() {
            AtomicInteger callCount = new AtomicInteger(0);
            var memo = GenericChallenges2.challenge10((Integer n) -> {
                callCount.incrementAndGet();
                return n * n;
            });

            assertEquals(25, memo.apply(5));
            assertEquals(1,  callCount.get()); // ← computed once!
        }

        @Test
        void cacheOnSubsequentCalls() {
            AtomicInteger callCount = new AtomicInteger(0);
            var memo = GenericChallenges2.challenge10((Integer n) -> {
                callCount.incrementAndGet();
                return n * n;
            });

            memo.apply(5); // ← computes
            memo.apply(5); // ← cached!
            memo.apply(5); // ← cached!

            assertEquals(1, callCount.get()); // ← only computed ONCE!
        }

        @Test
        void differentInputsComputedSeparately() {
            AtomicInteger callCount = new AtomicInteger(0);
            var memo = GenericChallenges2.challenge10((Integer n) -> {
                callCount.incrementAndGet();
                return n * n;
            });

            assertEquals(25, memo.apply(5));
            assertEquals(9,  memo.apply(3));
            assertEquals(2,  callCount.get()); // ← two different inputs!
        }

        @Test
        void cachedResultCorrect() {
            var memo = GenericChallenges2.challenge10(
                    (String s) -> s.toUpperCase());

            assertEquals("HELLO", memo.apply("hello"));
            assertEquals("HELLO", memo.apply("hello")); // ← cached!
            assertEquals("WORLD", memo.apply("world"));
        }

        @Test
        void sameInputNotRecomputed() {
            AtomicInteger callCount = new AtomicInteger(0);
            var memo = GenericChallenges2.challenge10((String s) -> {
                callCount.incrementAndGet();
                return s.length();
            });

            memo.apply("hello");
            memo.apply("world");
            memo.apply("hello"); // ← cached!
            memo.apply("world"); // ← cached!

            assertEquals(2, callCount.get()); // ← only 2 unique inputs!
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges2.challenge10(null));
        }
    }
}
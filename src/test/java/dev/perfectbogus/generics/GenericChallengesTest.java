package dev.perfectbogus.generics;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenericChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Generic swap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void swapStrings() {
            assertEquals(List.of("d","b","c","a"),
                    GenericChallenges.challenge1(List.of("a","b","c","d"), 0, 3));
        }

        @Test
        void swapIntegers() {
            assertEquals(List.of(1,4,3,2,5),
                    GenericChallenges.challenge1(List.of(1,2,3,4,5), 1, 3));
        }

        @Test
        void swapSameIndex() {
            assertEquals(List.of("a","b","c"),
                    GenericChallenges.challenge1(List.of("a","b","c"), 1, 1));
        }

        @Test
        void originalNotModified() {
            List<String> original = new ArrayList<>(List.of("a","b","c"));
            GenericChallenges.challenge1(original, 0, 2);
            assertEquals(List.of("a","b","c"), original); // ← unchanged!
        }

        @Test
        void outOfBoundsThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge1(List.of("a","b"), 0, 5));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge1(null, 0, 1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Generic max with Comparable bound
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void maxIntegers() {
            assertEquals(9,
                    GenericChallenges.challenge2(List.of(3,1,4,1,5,9,2,6)));
        }

        @Test
        void maxStrings() {
            assertEquals("cherry",
                    GenericChallenges.challenge2(List.of("banana","apple","cherry")));
        }

        @Test
        void maxDoubles() {
            assertEquals(3.14,
                    GenericChallenges.challenge2(List.of(3.14, 2.71, 1.41)), 0.001);
        }

        @Test
        void singleElement() {
            assertEquals(42, GenericChallenges.challenge2(List.of(42)));
        }

        @Test
        void allSame() {
            assertEquals(5, GenericChallenges.challenge2(List.of(5,5,5)));
        }

        @Test
        void emptyListThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge2(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Generic filter with Predicate
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void filterEvenNumbers() {
            assertEquals(List.of(2,4),
                    GenericChallenges.challenge3(List.of(1,2,3,4,5), n -> n % 2 == 0));
        }

        @Test
        void filterLongStrings() {
            assertEquals(List.of("hello","hey"),
                    GenericChallenges.challenge3(
                            List.of("hi","hello","hey"), s -> s.length() > 2));
        }

        @Test
        void noneMatch() {
            assertTrue(GenericChallenges.challenge3(
                    List.of(1,2,3), n -> n > 10).isEmpty());
        }

        @Test
        void allMatch() {
            assertEquals(List.of(2,4,6),
                    GenericChallenges.challenge3(List.of(2,4,6), n -> n % 2 == 0));
        }

        @Test
        void originalNotModified() {
            List<Integer> original = new ArrayList<>(List.of(1,2,3,4,5));
            GenericChallenges.challenge3(original, n -> n % 2 == 0);
            assertEquals(5, original.size()); // ← unchanged!
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges.challenge3(
                    List.of(), n -> true).isEmpty());
        }

        @Test
        void nullList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge3(null, n -> true));
        }

        @Test
        void nullPredicate() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge3(List.of(1,2,3), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Generic Pair<A,B>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void firstAndSecond() {
            GenericChallenges.Pair<String, Integer> pair =
                    GenericChallenges.challenge4("Alice", 95000);

            assertEquals("Alice", pair.first());
            assertEquals(95000,   pair.second());
        }

        @Test
        void swap() {
            GenericChallenges.Pair<String, Integer> pair =
                    GenericChallenges.challenge4("Alice", 95000);
            GenericChallenges.Pair<Integer, String> swapped = pair.swap();

            assertEquals(95000,   swapped.first());
            assertEquals("Alice", swapped.second());
        }

        @Test
        void toStringFormat() {
            GenericChallenges.Pair<String, Integer> pair =
                    GenericChallenges.challenge4("Alice", 95000);

            assertEquals("(Alice, 95000)", pair.toString());
        }

        @Test
        void differentTypes() {
            GenericChallenges.Pair<Integer, Double> pair =
                    GenericChallenges.challenge4(42, 3.14);

            assertEquals(42,   pair.first());
            assertEquals(3.14, pair.second(), 0.001);
        }

        @Test
        void swapTwiceIsOriginal() {
            GenericChallenges.Pair<String, Integer> pair =
                    GenericChallenges.challenge4("Alice", 42);
            GenericChallenges.Pair<String, Integer> result = pair.swap().swap();

            assertEquals(pair.first(),  result.first());
            assertEquals(pair.second(), result.second());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Wildcard sum of Numbers
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void sumIntegers() {
            assertEquals(15.0,
                    GenericChallenges.challenge5(List.of(1,2,3,4,5)), 0.001);
        }

        @Test
        void sumDoubles() {
            assertEquals(7.0,
                    GenericChallenges.challenge5(List.of(1.5, 2.5, 3.0)), 0.001);
        }

        @Test
        void sumLongs() {
            assertEquals(300.0,
                    GenericChallenges.challenge5(List.of(100L, 200L)), 0.001);
        }

        @Test
        void emptyList() {
            assertEquals(0.0,
                    GenericChallenges.challenge5(List.of()), 0.001);
        }

        @Test
        void singleElement() {
            assertEquals(42.0,
                    GenericChallenges.challenge5(List.of(42)), 0.001);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Generic Stack<T>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void pushAndPop() {
            GenericChallenges.Stack<Integer> stack = GenericChallenges.challenge6();
            stack.push(1);
            stack.push(2);
            stack.push(3);

            assertEquals(3, stack.pop());
            assertEquals(2, stack.pop());
            assertEquals(1, stack.pop());
        }

        @Test
        void peek() {
            GenericChallenges.Stack<String> stack = GenericChallenges.challenge6();
            stack.push("hello");
            stack.push("world");

            assertEquals("world", stack.peek()); // ← not removed!
            assertEquals("world", stack.peek()); // ← still there!
            assertEquals(2, stack.size());
        }

        @Test
        void isEmpty() {
            GenericChallenges.Stack<Integer> stack = GenericChallenges.challenge6();
            assertTrue(stack.isEmpty());
            stack.push(1);
            assertFalse(stack.isEmpty());
            stack.pop();
            assertTrue(stack.isEmpty());
        }

        @Test
        void size() {
            GenericChallenges.Stack<Integer> stack = GenericChallenges.challenge6();
            assertEquals(0, stack.size());
            stack.push(1);
            assertEquals(1, stack.size());
            stack.push(2);
            assertEquals(2, stack.size());
            stack.pop();
            assertEquals(1, stack.size());
        }

        @Test
        void popEmptyThrows() {
            GenericChallenges.Stack<Integer> stack = GenericChallenges.challenge6();
            assertThrows(NoSuchElementException.class, stack::pop);
        }

        @Test
        void peekEmptyThrows() {
            GenericChallenges.Stack<String> stack = GenericChallenges.challenge6();
            assertThrows(NoSuchElementException.class, stack::peek);
        }

        @Test
        void lifoOrder() {
            GenericChallenges.Stack<Integer> stack = GenericChallenges.challenge6();
            for (int i = 1; i <= 5; i++) stack.push(i);

            List<Integer> result = new ArrayList<>();
            while (!stack.isEmpty()) result.add(stack.pop());

            assertEquals(List.of(5,4,3,2,1), result); // ← LIFO!
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Generic zip
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicZip() {
            List<GenericChallenges.Pair<String, Integer>> result =
                    GenericChallenges.challenge7(
                            List.of("a","b","c"), List.of(1,2,3));

            assertEquals(3, result.size());
            assertEquals("a", result.get(0).first()); assertEquals(1, result.get(0).second());
            assertEquals("b", result.get(1).first()); assertEquals(2, result.get(1).second());
            assertEquals("c", result.get(2).first()); assertEquals(3, result.get(2).second());
        }

        @Test
        void differentLengthsUsesShorted() {
            List<GenericChallenges.Pair<String, Integer>> result =
                    GenericChallenges.challenge7(
                            List.of("a","b","c"), List.of(1,2));

            assertEquals(2, result.size()); // ← shorter list wins!
        }

        @Test
        void emptyFirstList() {
            List<GenericChallenges.Pair<String, Integer>> result =
                    GenericChallenges.challenge7(List.of(), List.of(1,2,3));

            assertTrue(result.isEmpty());
        }

        @Test
        void emptySecondList() {
            List<GenericChallenges.Pair<String, Integer>> result =
                    GenericChallenges.challenge7(List.of("a","b"), List.of());

            assertTrue(result.isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge7(null, List.of(1)));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Generic Result<T>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void okResult() {
            GenericChallenges.Result<Integer> result =
                    GenericChallenges.challenge8ok(42);

            assertTrue(result.isSuccess());
            assertEquals(42,   result.getValue());
            assertNull(result.getError());
        }

        @Test
        void errorResult() {
            GenericChallenges.Result<Integer> result =
                    GenericChallenges.challenge8error("not found");

            assertFalse(result.isSuccess());
            assertNull(result.getValue());
            assertEquals("not found", result.getError());
        }

        @Test
        void mapOnSuccess() {
            GenericChallenges.Result<Integer> result =
                    GenericChallenges.challenge8ok(42);
            GenericChallenges.Result<String> mapped =
                    result.map(n -> "Number: " + n);

            assertTrue(mapped.isSuccess());
            assertEquals("Number: 42", mapped.getValue());
        }

        @Test
        void mapOnErrorPropagates() {
            GenericChallenges.Result<Integer> result =
                    GenericChallenges.challenge8error("not found");
            GenericChallenges.Result<String> mapped =
                    result.map(n -> "Number: " + n); // ← never called!

            assertFalse(mapped.isSuccess());
            assertEquals("not found", mapped.getError()); // ← propagated!
        }

        @Test
        void chainedMap() {
            GenericChallenges.Result<String> result =
                    GenericChallenges.challenge8ok(10)
                            .map(n -> n * 2)
                            .map(n -> "Result: " + n);

            assertEquals("Result: 20", result.getValue());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Generic flatten
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void flattenIntegers() {
            assertEquals(List.of(1,2,3,4,5,6),
                    GenericChallenges.challenge9(
                            List.of(List.of(1,2,3), List.of(4,5), List.of(6))));
        }

        @Test
        void flattenStrings() {
            assertEquals(List.of("a","b","c"),
                    GenericChallenges.challenge9(
                            List.of(List.of("a","b"), List.of("c"))));
        }

        @Test
        void emptySubLists() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges.challenge9(
                            List.of(List.of(), List.of(1), List.of(), List.of(2,3))));
        }

        @Test
        void emptyOuterList() {
            assertTrue(GenericChallenges.challenge9(List.of()).isEmpty());
        }

        @Test
        void allEmptySubLists() {
            assertTrue(GenericChallenges.challenge9(
                    List.of(List.of(), List.of())).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Generic groupBy with classifier
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void groupByFirstChar() {
            Map<Character, List<String>> result =
                    GenericChallenges.challenge10(
                            List.of("apple","avocado","banana","blueberry","cherry"),
                            s -> s.charAt(0));

            assertEquals(List.of("apple","avocado"),       result.get('a'));
            assertEquals(List.of("banana","blueberry"),    result.get('b'));
            assertEquals(List.of("cherry"),                result.get('c'));
        }

        @Test
        void groupByEvenOdd() {
            Map<String, List<Integer>> result =
                    GenericChallenges.challenge10(
                            List.of(1,2,3,4,5,6),
                            n -> n % 2 == 0 ? "even" : "odd");

            assertEquals(List.of(2,4,6), result.get("even"));
            assertEquals(List.of(1,3,5), result.get("odd"));
        }

        @Test
        void groupByLength() {
            Map<Integer, List<String>> result =
                    GenericChallenges.challenge10(
                            List.of("cat","dog","elephant","ant"),
                            String::length);

            assertEquals(List.of("cat","dog","ant"), result.get(3));
            assertEquals(List.of("elephant"),        result.get(8));
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges.challenge10(List.of(), s -> s).isEmpty());
        }

        @Test
        void nullList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge10(null, s -> s));
        }

        @Test
        void nullClassifier() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges.challenge10(List.of("a"), null));
        }
    }
}
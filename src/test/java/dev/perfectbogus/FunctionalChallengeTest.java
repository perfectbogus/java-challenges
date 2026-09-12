package dev.perfectbogus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FunctionalChallengeTest {

    // ==========================================================
    // CHALLENGE 1: applyTwice
    // ==========================================================
    @Nested
    class ApplyTwiceTests {

        @Test
        void testIncrementTwice() {
            Function<Integer, Integer> increment = x -> x + 1;
            assertEquals(7, FunctionalChallenge.applyTwice(increment, 5));
        }

        @Test
        void testDoubleTwice() {
            Function<Integer, Integer> doubleIt = x -> x * 2;
            assertEquals(20, FunctionalChallenge.applyTwice(doubleIt, 5));
        }

        @Test
        void testSquareTwice() {
            Function<Integer, Integer> square = x -> x * x;
            // square(3) = 9, square(9) = 81
            assertEquals(81, FunctionalChallenge.applyTwice(square, 3));
        }

        @Test
        void testIdentityFunction() {
            Function<Integer, Integer> identity = x -> x;
            assertEquals(42, FunctionalChallenge.applyTwice(identity, 42));
        }

        @Test
        void testWithNegativeInput() {
            Function<Integer, Integer> addTen = x -> x + 10;
            assertEquals(20, FunctionalChallenge.applyTwice(addTen, 0));
        }
    }

    // ==========================================================
    // CHALLENGE 2: allMatch
    // ==========================================================
    @Nested
    class AllMatchTests {

        @Test
        void testAllPositive() {
            List<Integer> input = List.of(1, 2, 3, 4);
            Predicate<Integer> isPositive = n -> n > 0;
            assertTrue(FunctionalChallenge.allMatch(input, isPositive));
        }

        @Test
        void testNotAllEven() {
            List<Integer> input = List.of(2, 4, 5, 8);
            Predicate<Integer> isEven = n -> n % 2 == 0;
            assertFalse(FunctionalChallenge.allMatch(input, isEven));
        }

        @Test
        void testEmptyListReturnsTrue() {
            List<Integer> input = List.of();
            Predicate<Integer> isEven = n -> n % 2 == 0;
            assertTrue(FunctionalChallenge.allMatch(input, isEven));
        }

        @Test
        void testAllMatchGreaterThanThreshold() {
            List<Integer> input = List.of(10, 20, 30);
            Predicate<Integer> greaterThanFive = n -> n > 5;
            assertTrue(FunctionalChallenge.allMatch(input, greaterThanFive));
        }

        @Test
        void testSingleElementFails() {
            List<Integer> input = List.of(3);
            Predicate<Integer> isEven = n -> n % 2 == 0;
            assertFalse(FunctionalChallenge.allMatch(input, isEven));
        }
    }

    // ==========================================================
    // CHALLENGE 3: combineStrings
    // ==========================================================
    @Nested
    class CombineStringsTests {

        @Test
        void testBasicConcatenation() {
            Supplier<String> first = () -> "Hello, ";
            Supplier<String> second = () -> "World!";
            assertEquals("Hello, World!", FunctionalChallenge.combineStrings(first, second));
        }

        @Test
        void testEmptyStrings() {
            Supplier<String> first = () -> "";
            Supplier<String> second = () -> "";
            assertEquals("", FunctionalChallenge.combineStrings(first, second));
        }

        @Test
        void testFirstEmpty() {
            Supplier<String> first = () -> "";
            Supplier<String> second = () -> "Java";
            assertEquals("Java", FunctionalChallenge.combineStrings(first, second));
        }

        @Test
        void testSecondEmpty() {
            Supplier<String> first = () -> "Java";
            Supplier<String> second = () -> "";
            assertEquals("Java", FunctionalChallenge.combineStrings(first, second));
        }

        @Test
        void testOrderMatters() {
            Supplier<String> first = () -> "foo";
            Supplier<String> second = () -> "bar";
            assertEquals("foobar", FunctionalChallenge.combineStrings(first, second));
        }
    }

    // ==========================================================
    // CHALLENGE 4: composeAndApply
    // ==========================================================
    @Nested
    class ComposeAndApplyTests {

        @Test
        void testAddThenMultiply() {
            Function<Integer, Integer> addTwo = x -> x + 2;
            Function<Integer, Integer> timesThree = x -> x * 3;
            // (5 + 2) * 3 = 21
            assertEquals(21, FunctionalChallenge.composeAndApply(addTwo, timesThree, 5));
        }

        @Test
        void testMultiplyThenAdd() {
            Function<Integer, Integer> timesTwo = x -> x * 2;
            Function<Integer, Integer> addFive = x -> x + 5;
            // (4 * 2) + 5 = 13
            assertEquals(13, FunctionalChallenge.composeAndApply(timesTwo, addFive, 4));
        }

        @Test
        void testOrderOfCompositionMatters() {
            Function<Integer, Integer> square = x -> x * x;
            Function<Integer, Integer> addOne = x -> x + 1;
            // square(2)=4, then +1 = 5
            assertEquals(5, FunctionalChallenge.composeAndApply(square, addOne, 2));
        }

        @Test
        void testWithIdentityFirst() {
            Function<Integer, Integer> identity = x -> x;
            Function<Integer, Integer> negate = x -> -x;
            assertEquals(-7, FunctionalChallenge.composeAndApply(identity, negate, 7));
        }

        @Test
        void testWithIdentitySecond() {
            Function<Integer, Integer> square = x -> x * x;
            Function<Integer, Integer> identity = x -> x;
            assertEquals(16, FunctionalChallenge.composeAndApply(square, identity, 4));
        }
    }

    // ==========================================================
    // CHALLENGE 5: countMatching
    // ==========================================================
    @Nested
    class CountMatchingTests {

        @Test
        void testCountWordsLongerThanThree() {
            List<String> input = List.of("cat", "elephant", "dog", "giraffe");
            Predicate<String> longerThanThree = w -> w.length() > 3;
            assertEquals(2, FunctionalChallenge.countMatching(input, longerThanThree));
        }

        @Test
        void testCountStartingWithLetter() {
            List<String> input = List.of("apple", "banana", "avocado", "cherry");
            Predicate<String> startsWithA = w -> w.startsWith("a");
            assertEquals(2, FunctionalChallenge.countMatching(input, startsWithA));
        }

        @Test
        void testEmptyListReturnsZero() {
            List<String> input = List.of();
            Predicate<String> anyWord = w -> true;
            assertEquals(0, FunctionalChallenge.countMatching(input, anyWord));
        }

        @Test
        void testNoMatches() {
            List<String> input = List.of("dog", "cat", "cow");
            Predicate<String> startsWithZ = w -> w.startsWith("z");
            assertEquals(0, FunctionalChallenge.countMatching(input, startsWithZ));
        }

        @Test
        void testAllMatch() {
            List<String> input = List.of("aa", "bb", "cc");
            Predicate<String> lengthTwo = w -> w.length() == 2;
            assertEquals(3, FunctionalChallenge.countMatching(input, lengthTwo));
        }
    }
}
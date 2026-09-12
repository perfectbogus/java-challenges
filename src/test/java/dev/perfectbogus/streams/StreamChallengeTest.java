package dev.perfectbogus.streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StreamChallengeTest {

    // ==========================================================
    // CHALLENGE 1: groupByLength
    // ==========================================================
    @Nested
    class GroupByLengthTests {

        @Test
        void testBasicGrouping() {
            List<String> input = List.of("cat", "dog", "bird", "ox", "fox", "lion");
            Map<Integer, List<String>> expected = Map.of(
                    3, List.of("cat", "dog", "fox"),
                    4, List.of("bird", "lion"),
                    2, List.of("ox")
            );
            assertEquals(expected, StreamChallenge.groupByLength(input));
        }

        @Test
        void testEmptyList() {
            List<String> input = List.of();
            Map<Integer, List<String>> expected = Map.of();
            assertEquals(expected, StreamChallenge.groupByLength(input));
        }

        @Test
        void testSingleWord() {
            List<String> input = List.of("hello");
            Map<Integer, List<String>> expected = Map.of(5, List.of("hello"));
            assertEquals(expected, StreamChallenge.groupByLength(input));
        }

        @Test
        void testAllSameLength() {
            List<String> input = List.of("aa", "bb", "cc");
            Map<Integer, List<String>> expected = Map.of(2, List.of("aa", "bb", "cc"));
            assertEquals(expected, StreamChallenge.groupByLength(input));
        }

        @Test
        void testPreservesInsertionOrderWithinGroup() {
            List<String> input = List.of("bb", "aa", "cc");
            List<String> resultForLength2 = StreamChallenge.groupByLength(input).get(2);
            assertEquals(List.of("bb", "aa", "cc"), resultForLength2);
        }

        @Test
        void testWordsWithDuplicates() {
            List<String> input = List.of("cat", "cat", "dog");
            Map<Integer, List<String>> expected = Map.of(3, List.of("cat", "cat", "dog"));
            assertEquals(expected, StreamChallenge.groupByLength(input));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sumOfSquaresOfEvens
    // ==========================================================
    @Nested
    class SumOfSquaresOfEvensTests {

        @Test
        void testBasicMixedNumbers() {
            List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
            // evens: 2, 4, 6 -> squares: 4, 16, 36 -> sum = 56
            assertEquals(56, StreamChallenge.sumOfSquaresOfEvens(input));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, StreamChallenge.sumOfSquaresOfEvens(List.of()));
        }

        @Test
        void testAllOdd() {
            List<Integer> input = List.of(1, 3, 5, 7);
            assertEquals(0, StreamChallenge.sumOfSquaresOfEvens(input));
        }

        @Test
        void testAllEven() {
            List<Integer> input = List.of(2, 4);
            // squares: 4, 16 -> sum = 20
            assertEquals(20, StreamChallenge.sumOfSquaresOfEvens(input));
        }

        @Test
        void testWithNegativeEvens() {
            List<Integer> input = List.of(-2, 3, -4);
            // evens: -2, -4 -> squares: 4, 16 -> sum = 20
            assertEquals(20, StreamChallenge.sumOfSquaresOfEvens(input));
        }

        @Test
        void testWithZero() {
            List<Integer> input = List.of(0, 1, 2);
            // evens: 0, 2 -> squares: 0, 4 -> sum = 4
            assertEquals(4, StreamChallenge.sumOfSquaresOfEvens(input));
        }
    }

    // ==========================================================
    // CHALLENGE 3: longestWordStartingWith
    // ==========================================================
    @Nested
    class LongestWordStartingWithTests {

        @Test
        void testBasicMatch() {
            List<String> input = List.of("banana", "bee", "berry", "apple");
            assertEquals(Optional.of("banana"), StreamChallenge.longestWordStartingWith(input, 'b'));
        }

        @Test
        void testCaseInsensitiveLetter() {
            List<String> input = List.of("Banana", "bee", "Berry");
            assertEquals(Optional.of("Banana"), StreamChallenge.longestWordStartingWith(input, 'B'));
        }

        @Test
        void testNoMatch() {
            List<String> input = List.of("apple", "orange", "grape");
            assertEquals(Optional.empty(), StreamChallenge.longestWordStartingWith(input, 'z'));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), StreamChallenge.longestWordStartingWith(List.of(), 'a'));
        }

        @Test
        void testTieReturnsFirstEncountered() {
            List<String> input = List.of("cat", "car", "cup", "cow");
            // "cat", "car", "cow" all length 3, "cat" comes first
            assertEquals(Optional.of("cat"), StreamChallenge.longestWordStartingWith(input, 'c'));
        }

        @Test
        void testSingleMatchingWord() {
            List<String> input = List.of("dog", "cat", "elephant");
            assertEquals(Optional.of("elephant"), StreamChallenge.longestWordStartingWith(input, 'e'));
        }
    }
}
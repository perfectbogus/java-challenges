package dev.perfectbogus.streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sumOfSquares
    // ==========================================================
    @Nested
    class SumOfSquaresTests {

        @Test
        void testMixedNumbers() {
            assertEquals(14, StreamIntermediateChallenge.sumOfSquares(List.of(1, 2, 3)));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, StreamIntermediateChallenge.sumOfSquares(List.of()));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(13, StreamIntermediateChallenge.sumOfSquares(List.of(-2, 3)));
        }

        @Test
        void testSingleElement() {
            assertEquals(25, StreamIntermediateChallenge.sumOfSquares(List.of(5)));
        }
    }

    // ==========================================================
    // CHALLENGE 2: filterAndSortEven
    // ==========================================================
    @Nested
    class FilterAndSortEvenTests {

        @Test
        void testMixedNumbers() {
            assertEquals(List.of(2, 4, 8), StreamIntermediateChallenge.filterAndSortEven(List.of(5, 3, 2, 8, 1, 4)));
        }

        @Test
        void testNoEvenNumbers() {
            assertEquals(List.of(), StreamIntermediateChallenge.filterAndSortEven(List.of(1, 3, 5)));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamIntermediateChallenge.filterAndSortEven(List.of()));
        }

        @Test
        void testNegativeEvenNumbers() {
            assertEquals(List.of(-4, -2), StreamIntermediateChallenge.filterAndSortEven(List.of(-4, -1, -2)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: joinWithCommas
    // ==========================================================
    @Nested
    class JoinWithCommasTests {

        @Test
        void testMultipleItems() {
            assertEquals("a, b, c", StreamIntermediateChallenge.joinWithCommas(List.of("a", "b", "c")));
        }

        @Test
        void testSingleItem() {
            assertEquals("only", StreamIntermediateChallenge.joinWithCommas(List.of("only")));
        }

        @Test
        void testEmptyList() {
            assertEquals("", StreamIntermediateChallenge.joinWithCommas(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: countLongerThan
    // ==========================================================
    @Nested
    class CountLongerThanTests {

        @Test
        void testSomeMatch() {
            assertEquals(2, StreamIntermediateChallenge.countLongerThan(List.of("a", "bb", "ccc", "dddd"), 2));
        }

        @Test
        void testNoneMatch() {
            assertEquals(0, StreamIntermediateChallenge.countLongerThan(List.of("x"), 5));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, StreamIntermediateChallenge.countLongerThan(List.of(), 1));
        }
    }

    // ==========================================================
    // CHALLENGE 5: groupByLength
    // ==========================================================
    @Nested
    class GroupByLengthTests {

        @Test
        void testGroupsByLength() {
            Map<Integer, List<String>> expected = Map.of(
                    1, List.of("a", "b"),
                    2, List.of("cc", "dd"),
                    3, List.of("eee")
            );
            assertEquals(expected, StreamIntermediateChallenge.groupByLength(List.of("a", "b", "cc", "dd", "eee")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), StreamIntermediateChallenge.groupByLength(List.of()));
        }

        @Test
        void testAllSameLength() {
            Map<Integer, List<String>> expected = Map.of(2, List.of("ab", "cd", "ef"));
            assertEquals(expected, StreamIntermediateChallenge.groupByLength(List.of("ab", "cd", "ef")));
        }
    }

    // ==========================================================
    // CHALLENGE 6: distinctSortedIgnoreCase
    // ==========================================================
    @Nested
    class DistinctSortedIgnoreCaseTests {

        @Test
        void testRemovesCaseInsensitiveDuplicates() {
            assertEquals(
                    List.of("apple", "banana", "cherry"),
                    StreamIntermediateChallenge.distinctSortedIgnoreCase(
                            List.of("Banana", "apple", "APPLE", "cherry", "banana"))
            );
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamIntermediateChallenge.distinctSortedIgnoreCase(List.of()));
        }

        @Test
        void testAlreadyDistinct() {
            assertEquals(List.of("x", "y", "z"), StreamIntermediateChallenge.distinctSortedIgnoreCase(List.of("z", "x", "y")));
        }
    }

    // ==========================================================
    // CHALLENGE 7: average
    // ==========================================================
    @Nested
    class AverageTests {

        @Test
        void testMultipleNumbers() {
            assertEquals(4.0, StreamIntermediateChallenge.average(List.of(2, 4, 6)));
        }

        @Test
        void testEmptyList() {
            assertEquals(0.0, StreamIntermediateChallenge.average(List.of()));
        }

        @Test
        void testSingleElement() {
            assertEquals(5.0, StreamIntermediateChallenge.average(List.of(5)));
        }

        @Test
        void testNonIntegerAverage() {
            assertEquals(1.5, StreamIntermediateChallenge.average(List.of(1, 2)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: longestWord
    // ==========================================================
    @Nested
    class LongestWordTests {

        @Test
        void testFindsLongest() {
            assertEquals(Optional.of("ccc"), StreamIntermediateChallenge.longestWord(List.of("a", "bb", "ccc", "dd")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), StreamIntermediateChallenge.longestWord(List.of()));
        }

        @Test
        void testTieReturnsFirst() {
            assertEquals(Optional.of("same"), StreamIntermediateChallenge.longestWord(List.of("same", "size")));
        }
    }

    // ==========================================================
    // CHALLENGE 9: flatten
    // ==========================================================
    @Nested
    class FlattenTests {

        @Test
        void testFlattensPreservingOrder() {
            assertEquals(
                    List.of(1, 2, 3, 4, 5),
                    StreamIntermediateChallenge.flatten(List.of(List.of(1, 2), List.of(3), List.of(), List.of(4, 5)))
            );
        }

        @Test
        void testEmptyOuterList() {
            assertEquals(List.of(), StreamIntermediateChallenge.flatten(List.of()));
        }

        @Test
        void testAllEmptyInnerLists() {
            assertEquals(List.of(), StreamIntermediateChallenge.flatten(List.of(List.of(), List.of())));
        }
    }

    // ==========================================================
    // CHALLENGE 10: partitionEvenOdd
    // ==========================================================
    @Nested
    class PartitionEvenOddTests {

        @Test
        void testMixedNumbers() {
            Map<Boolean, List<Integer>> expected = Map.of(
                    false, List.of(1, 3, 5),
                    true, List.of(2, 4)
            );
            assertEquals(expected, StreamIntermediateChallenge.partitionEvenOdd(List.of(1, 2, 3, 4, 5)));
        }

        @Test
        void testEmptyList() {
            Map<Boolean, List<Integer>> expected = Map.of(false, List.of(), true, List.of());
            assertEquals(expected, StreamIntermediateChallenge.partitionEvenOdd(List.of()));
        }

        @Test
        void testAllEven() {
            Map<Boolean, List<Integer>> expected = Map.of(false, List.of(), true, List.of(2, 4));
            assertEquals(expected, StreamIntermediateChallenge.partitionEvenOdd(List.of(2, 4)));
        }
    }

    // ==========================================================
    // CHALLENGE 11: wordFrequency
    // ==========================================================
    @Nested
    class WordFrequencyTests {

        @Test
        void testCountsOccurrences() {
            Map<String, Long> expected = Map.of("a", 3L, "b", 2L, "c", 1L);
            assertEquals(expected, StreamIntermediateChallenge.wordFrequency(List.of("a", "b", "a", "c", "b", "a")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), StreamIntermediateChallenge.wordFrequency(List.of()));
        }

        @Test
        void testAllUnique() {
            Map<String, Long> expected = Map.of("x", 1L, "y", 1L);
            assertEquals(expected, StreamIntermediateChallenge.wordFrequency(List.of("x", "y")));
        }
    }

    // ==========================================================
    // CHALLENGE 12: topNKeysByValue
    // ==========================================================
    @Nested
    class TopNKeysByValueTests {

        @Test
        void testTiesBrokenAlphabetically() {
            Map<String, Integer> scores = Map.of("alice", 90, "bob", 85, "carol", 90, "dave", 70);
            assertEquals(List.of("alice", "carol"), StreamIntermediateChallenge.topNKeysByValue(scores, 2));
        }

        @Test
        void testZeroReturnsEmptyList() {
            Map<String, Integer> scores = Map.of("alice", 90, "bob", 85);
            assertEquals(List.of(), StreamIntermediateChallenge.topNKeysByValue(scores, 0));
        }

        @Test
        void testNGreaterThanSizeReturnsAll() {
            Map<String, Integer> scores = Map.of("alice", 90, "bob", 85);
            assertEquals(List.of("alice", "bob"), StreamIntermediateChallenge.topNKeysByValue(scores, 5));
        }
    }

    // ==========================================================
    // CHALLENGE 13: firstLettersUppercase
    // ==========================================================
    @Nested
    class FirstLettersUppercaseTests {

        @Test
        void testBasicWords() {
            assertEquals("HW", StreamIntermediateChallenge.firstLettersUppercase(List.of("hello", "world")));
        }

        @Test
        void testSkipsEmptyStrings() {
            assertEquals("AB", StreamIntermediateChallenge.firstLettersUppercase(List.of("", "a", "bee")));
        }

        @Test
        void testEmptyList() {
            assertEquals("", StreamIntermediateChallenge.firstLettersUppercase(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 14: sumOfDigits
    // ==========================================================
    @Nested
    class SumOfDigitsTests {

        @Test
        void testMultiDigitNumber() {
            assertEquals(15, StreamIntermediateChallenge.sumOfDigits(12345));
        }

        @Test
        void testZero() {
            assertEquals(0, StreamIntermediateChallenge.sumOfDigits(0));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(14, StreamIntermediateChallenge.sumOfDigits(-482));
        }
    }

    // ==========================================================
    // CHALLENGE 15: product
    // ==========================================================
    @Nested
    class ProductTests {

        @Test
        void testMultipleNumbers() {
            assertEquals(24, StreamIntermediateChallenge.product(List.of(1, 2, 3, 4)));
        }

        @Test
        void testEmptyListReturnsOne() {
            assertEquals(1, StreamIntermediateChallenge.product(List.of()));
        }

        @Test
        void testSingleElement() {
            assertEquals(5, StreamIntermediateChallenge.product(List.of(5)));
        }

        @Test
        void testIncludesNegativeNumber() {
            assertEquals(-6, StreamIntermediateChallenge.product(List.of(2, -3)));
        }
    }
}
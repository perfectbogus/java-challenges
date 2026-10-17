package dev.perfectbogus.arrays.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortStreamChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sortedAscending
    // ==========================================================
    @Nested
    class SortedAscendingTests {

        @Test
        void testSortsFromSmallestToGreatest() {
            assertArrayEquals(new int[]{1, 3, 5, 9}, ArraySortStreamChallenge.sortedAscending(new int[]{5, 3, 9, 1}));
        }

        @Test
        void testKeepsRepeatedNumbersAndNegatives() {
            assertArrayEquals(new int[]{-4, 2, 2}, ArraySortStreamChallenge.sortedAscending(new int[]{2, 2, -4}));
        }

        @Test
        void testAlreadySortedAndReversed() {
            assertArrayEquals(new int[]{1, 2, 3}, ArraySortStreamChallenge.sortedAscending(new int[]{1, 2, 3}));
            assertArrayEquals(new int[]{1, 2, 3}, ArraySortStreamChallenge.sortedAscending(new int[]{3, 2, 1}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                    ArraySortStreamChallenge.sortedAscending(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}));
        }

        @Test
        void testEmptyAndSingle() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedAscending(new int[]{}));
            assertArrayEquals(new int[]{7}, ArraySortStreamChallenge.sortedAscending(new int[]{7}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {5, 3, 9, 1};

            ArraySortStreamChallenge.sortedAscending(input);

            assertArrayEquals(new int[]{5, 3, 9, 1}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortedDescending
    // ==========================================================
    @Nested
    class SortedDescendingTests {

        @Test
        void testSortsFromGreatestToSmallest() {
            assertArrayEquals(new int[]{9, 5, 3, 1}, ArraySortStreamChallenge.sortedDescending(new int[]{5, 3, 9, 1}));
        }

        @Test
        void testKeepsRepeatedNumbersAndNegatives() {
            assertArrayEquals(new int[]{2, 2, -4}, ArraySortStreamChallenge.sortedDescending(new int[]{2, -4, 2}));
        }

        @Test
        void testAlreadySortedDescending() {
            assertArrayEquals(new int[]{3, 2, 1}, ArraySortStreamChallenge.sortedDescending(new int[]{3, 2, 1}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE},
                    ArraySortStreamChallenge.sortedDescending(new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE}));
        }

        @Test
        void testEmptyAndSingle() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedDescending(new int[]{}));
            assertArrayEquals(new int[]{7}, ArraySortStreamChallenge.sortedDescending(new int[]{7}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {5, 3, 9, 1};

            ArraySortStreamChallenge.sortedDescending(input);

            assertArrayEquals(new int[]{5, 3, 9, 1}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortedByLength
    // ==========================================================
    @Nested
    class SortedByLengthTests {

        @Test
        void testSortsFromShortestToLongest() {
            assertArrayEquals(new String[]{"fig", "pear", "kiwi", "apple"},
                    ArraySortStreamChallenge.sortedByLength(new String[]{"pear", "fig", "apple", "kiwi"}));
        }

        @Test
        void testSameLengthKeepsTheOriginalOrder() {
            assertArrayEquals(new String[]{"c", "bb", "aa"},
                    ArraySortStreamChallenge.sortedByLength(new String[]{"bb", "aa", "c"}));
            assertArrayEquals(new String[]{"zz", "yy", "xx", "ww"},
                    ArraySortStreamChallenge.sortedByLength(new String[]{"zz", "yy", "xx", "ww"}));
        }

        @Test
        void testSameLengthIsNotSortedAlphabetically() {
            assertArrayEquals(new String[]{"b", "a", "ccc", "ddd", "bbb"},
                    ArraySortStreamChallenge.sortedByLength(new String[]{"ccc", "b", "ddd", "a", "bbb"}));
        }

        @Test
        void testEmptyStringsGoFirst() {
            assertArrayEquals(new String[]{"", "", "a"},
                    ArraySortStreamChallenge.sortedByLength(new String[]{"a", "", ""}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new String[]{}, ArraySortStreamChallenge.sortedByLength(new String[]{}));
        }

        @Test
        void testTheInputIsNotModified() {
            String[] input = {"pear", "fig", "apple", "kiwi"};

            ArraySortStreamChallenge.sortedByLength(input);

            assertArrayEquals(new String[]{"pear", "fig", "apple", "kiwi"}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortedIgnoringCase
    // ==========================================================
    @Nested
    class SortedIgnoringCaseTests {

        @Test
        void testAlphabeticalIgnoringCase() {
            assertArrayEquals(new String[]{"Apple", "banana", "cherry"},
                    ArraySortStreamChallenge.sortedIgnoringCase(new String[]{"banana", "Apple", "cherry"}));
        }

        @Test
        void testUpperCaseIsNotAlwaysFirst() {
            assertArrayEquals(new String[]{"apple", "Banana", "Cherry", "date"},
                    ArraySortStreamChallenge.sortedIgnoringCase(new String[]{"date", "Cherry", "apple", "Banana"}));
        }

        @Test
        void testEqualWordsKeepTheOriginalOrder() {
            assertArrayEquals(new String[]{"A", "a", "b", "B"},
                    ArraySortStreamChallenge.sortedIgnoringCase(new String[]{"b", "A", "B", "a"}));
            assertArrayEquals(new String[]{"a", "A", "a"},
                    ArraySortStreamChallenge.sortedIgnoringCase(new String[]{"a", "A", "a"}));
        }

        @Test
        void testShorterWordFirstWhenItIsThePrefix() {
            assertArrayEquals(new String[]{"Car", "cart", "Cartoon"},
                    ArraySortStreamChallenge.sortedIgnoringCase(new String[]{"Cartoon", "cart", "Car"}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new String[]{}, ArraySortStreamChallenge.sortedIgnoringCase(new String[]{}));
        }

        @Test
        void testTheInputIsNotModified() {
            String[] input = {"banana", "Apple", "cherry"};

            ArraySortStreamChallenge.sortedIgnoringCase(input);

            assertArrayEquals(new String[]{"banana", "Apple", "cherry"}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortedByAbsoluteValue
    // ==========================================================
    @Nested
    class SortedByAbsoluteValueTests {

        @Test
        void testSortsByAbsoluteValue() {
            assertArrayEquals(new int[]{0, 4, -5},
                    ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{-5, 4, 0}));
        }

        @Test
        void testSameAbsoluteValueSmallerNumberFirst() {
            assertArrayEquals(new int[]{-1, 2, -3, 3},
                    ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{3, -1, -3, 2}));
            assertArrayEquals(new int[]{-3, 3},
                    ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{-3, 3}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[]{-2, -2, 2, 2},
                    ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{2, -2, 2, -2}));
        }

        @Test
        void testAllNegative() {
            assertArrayEquals(new int[]{-1, -4, -9},
                    ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{-9, -1, -4}));
        }

        @Test
        void testEmptyAndSingle() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{}));
            assertArrayEquals(new int[]{-7}, ArraySortStreamChallenge.sortedByAbsoluteValue(new int[]{-7}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {3, -1, -3, 2};

            ArraySortStreamChallenge.sortedByAbsoluteValue(input);

            assertArrayEquals(new int[]{3, -1, -3, 2}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 6: topThree
    // ==========================================================
    @Nested
    class TopThreeTests {

        @Test
        void testThreeGreatestFromTheGreatest() {
            assertArrayEquals(new int[]{9, 7, 4}, ArraySortStreamChallenge.topThree(new int[]{4, 9, 1, 7, 3}));
        }

        @Test
        void testRepeatedNumbersCountAsManyTimes() {
            assertArrayEquals(new int[]{5, 5, 5}, ArraySortStreamChallenge.topThree(new int[]{5, 5, 5, 1}));
            assertArrayEquals(new int[]{9, 9, 2}, ArraySortStreamChallenge.topThree(new int[]{9, 2, 9, 1}));
        }

        @Test
        void testFewerThanThree() {
            assertArrayEquals(new int[]{8, 2}, ArraySortStreamChallenge.topThree(new int[]{2, 8}));
            assertArrayEquals(new int[]{4}, ArraySortStreamChallenge.topThree(new int[]{4}));
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.topThree(new int[]{}));
        }

        @Test
        void testExactlyThree() {
            assertArrayEquals(new int[]{3, 2, 1}, ArraySortStreamChallenge.topThree(new int[]{1, 3, 2}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-1, -2, -3}, ArraySortStreamChallenge.topThree(new int[]{-5, -3, -1, -2, -9}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {4, 9, 1, 7, 3};

            ArraySortStreamChallenge.topThree(input);

            assertArrayEquals(new int[]{4, 9, 1, 7, 3}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortedDistinct
    // ==========================================================
    @Nested
    class SortedDistinctTests {

        @Test
        void testRemovesRepeatedAndSorts() {
            assertArrayEquals(new int[]{1, 3, 4}, ArraySortStreamChallenge.sortedDistinct(new int[]{4, 1, 4, 3, 1}));
        }

        @Test
        void testAllEqual() {
            assertArrayEquals(new int[]{7}, ArraySortStreamChallenge.sortedDistinct(new int[]{7, 7, 7}));
        }

        @Test
        void testAlreadyDistinct() {
            assertArrayEquals(new int[]{-2, 0, 5}, ArraySortStreamChallenge.sortedDistinct(new int[]{5, -2, 0}));
        }

        @Test
        void testNegativeRepeated() {
            assertArrayEquals(new int[]{-3, -1}, ArraySortStreamChallenge.sortedDistinct(new int[]{-1, -3, -1, -3}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedDistinct(new int[]{}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {4, 1, 4, 3, 1};

            ArraySortStreamChallenge.sortedDistinct(input);

            assertArrayEquals(new int[]{4, 1, 4, 3, 1}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortedByLengthThenAlphabet
    // ==========================================================
    @Nested
    class SortedByLengthThenAlphabetTests {

        @Test
        void testLongestFirstThenAlphabetical() {
            assertArrayEquals(new String[]{"apple", "kiwi", "pear", "plum", "fig"},
                    ArraySortStreamChallenge.sortedByLengthThenAlphabet(
                            new String[]{"fig", "apple", "kiwi", "pear", "plum"}));
        }

        @Test
        void testShortWordsAreAlphabetical() {
            assertArrayEquals(new String[]{"cc", "a", "b"},
                    ArraySortStreamChallenge.sortedByLengthThenAlphabet(new String[]{"b", "a", "cc"}));
        }

        @Test
        void testSameLengthIsNotKeptInOriginalOrder() {
            assertArrayEquals(new String[]{"aaa", "bbb", "ccc"},
                    ArraySortStreamChallenge.sortedByLengthThenAlphabet(new String[]{"ccc", "aaa", "bbb"}));
        }

        @Test
        void testUpperCaseComesBeforeLowerCase() {
            assertArrayEquals(new String[]{"Bb", "aa"},
                    ArraySortStreamChallenge.sortedByLengthThenAlphabet(new String[]{"aa", "Bb"}));
        }

        @Test
        void testRepeatedWords() {
            assertArrayEquals(new String[]{"ab", "ab", "c"},
                    ArraySortStreamChallenge.sortedByLengthThenAlphabet(new String[]{"c", "ab", "ab"}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new String[]{}, ArraySortStreamChallenge.sortedByLengthThenAlphabet(new String[]{}));
        }

        @Test
        void testTheInputIsNotModified() {
            String[] input = {"fig", "apple", "kiwi"};

            ArraySortStreamChallenge.sortedByLengthThenAlphabet(input);

            assertArrayEquals(new String[]{"fig", "apple", "kiwi"}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 9: evensThenOdds
    // ==========================================================
    @Nested
    class EvensThenOddsTests {

        @Test
        void testEvensThenOddsEachSorted() {
            assertArrayEquals(new int[]{2, 4, 8, 1, 3, 5},
                    ArraySortStreamChallenge.evensThenOdds(new int[]{5, 2, 8, 3, 1, 4}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-2, 0, -3, 7},
                    ArraySortStreamChallenge.evensThenOdds(new int[]{-3, -2, 7, 0}));
            assertArrayEquals(new int[]{-8, -4, -9, -5, -1},
                    ArraySortStreamChallenge.evensThenOdds(new int[]{-1, -4, -5, -8, -9}));
        }

        @Test
        void testOnlyEvens() {
            assertArrayEquals(new int[]{2, 4, 6}, ArraySortStreamChallenge.evensThenOdds(new int[]{6, 2, 4}));
        }

        @Test
        void testOnlyOdds() {
            assertArrayEquals(new int[]{1, 3, 5}, ArraySortStreamChallenge.evensThenOdds(new int[]{5, 1, 3}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[]{2, 2, 1, 1}, ArraySortStreamChallenge.evensThenOdds(new int[]{1, 2, 1, 2}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.evensThenOdds(new int[]{}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {5, 2, 8, 3, 1, 4};

            ArraySortStreamChallenge.evensThenOdds(input);

            assertArrayEquals(new int[]{5, 2, 8, 3, 1, 4}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 10: isSortedAscending
    // ==========================================================
    @Nested
    class IsSortedAscendingTests {

        @Test
        void testSortedWithRepeatedNumbers() {
            assertTrue(ArraySortStreamChallenge.isSortedAscending(new int[]{1, 2, 2, 5}));
        }

        @Test
        void testNotSorted() {
            assertFalse(ArraySortStreamChallenge.isSortedAscending(new int[]{1, 3, 2}));
        }

        @Test
        void testOnlyTheLastPairIsWrong() {
            assertFalse(ArraySortStreamChallenge.isSortedAscending(new int[]{1, 2, 3, 4, 3}));
        }

        @Test
        void testOnlyTheFirstPairIsWrong() {
            assertFalse(ArraySortStreamChallenge.isSortedAscending(new int[]{2, 1, 3, 4}));
        }

        @Test
        void testDescendingIsNotSorted() {
            assertFalse(ArraySortStreamChallenge.isSortedAscending(new int[]{5, 4, 3}));
        }

        @Test
        void testAllEqual() {
            assertTrue(ArraySortStreamChallenge.isSortedAscending(new int[]{4, 4, 4}));
        }

        @Test
        void testEmptyAndSingle() {
            assertTrue(ArraySortStreamChallenge.isSortedAscending(new int[]{}));
            assertTrue(ArraySortStreamChallenge.isSortedAscending(new int[]{9}));
        }

        @Test
        void testExtremeValuesDoNotOverflow() {
            assertTrue(ArraySortStreamChallenge.isSortedAscending(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
            assertFalse(ArraySortStreamChallenge.isSortedAscending(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {3, 1, 2};

            ArraySortStreamChallenge.isSortedAscending(input);

            assertArrayEquals(new int[]{3, 1, 2}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 11: secondLargest
    // ==========================================================
    @Nested
    class SecondLargestTests {

        @Test
        void testSecondGreatestDifferentValue() {
            assertEquals(5, ArraySortStreamChallenge.secondLargest(new int[]{5, 9, 9, 1}));
        }

        @Test
        void testInAnyOrder() {
            assertEquals(8, ArraySortStreamChallenge.secondLargest(new int[]{1, 8, 3, 10, 2}));
            assertEquals(8, ArraySortStreamChallenge.secondLargest(new int[]{10, 8}));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-7, ArraySortStreamChallenge.secondLargest(new int[]{-7, -2}));
        }

        @Test
        void testAllEqualGivesNull() {
            assertNull(ArraySortStreamChallenge.secondLargest(new int[]{4, 4, 4}));
        }

        @Test
        void testSingleAndEmptyGiveNull() {
            assertNull(ArraySortStreamChallenge.secondLargest(new int[]{4}));
            assertNull(ArraySortStreamChallenge.secondLargest(new int[]{}));
        }

        @Test
        void testTheGreatestRepeatedManyTimes() {
            assertEquals(1, ArraySortStreamChallenge.secondLargest(new int[]{9, 9, 9, 9, 1}));
        }

        @Test
        void testSmallestValueCanBeTheSecond() {
            assertEquals(Integer.MIN_VALUE,
                    ArraySortStreamChallenge.secondLargest(new int[]{Integer.MIN_VALUE, 0, 0}));
        }

        @Test
        void testTheInputIsNotModified() {
            int[] input = {5, 9, 9, 1};

            ArraySortStreamChallenge.secondLargest(input);

            assertArrayEquals(new int[]{5, 9, 9, 1}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 12: sortedNumbersFromText
    // ==========================================================
    @Nested
    class SortedNumbersFromTextTests {

        @Test
        void testSortsAsNumbersNotAsText() {
            assertArrayEquals(new int[]{1, 3, 10, 25}, ArraySortStreamChallenge.sortedNumbersFromText("10, 3, 25,1"));
        }

        @Test
        void testSpacesAroundNumbersAndNegatives() {
            assertArrayEquals(new int[]{-4, 7}, ArraySortStreamChallenge.sortedNumbersFromText(" -4 , 7 "));
        }

        @Test
        void testRepeatedNumbersAreKept() {
            assertArrayEquals(new int[]{2, 2, 9}, ArraySortStreamChallenge.sortedNumbersFromText("9,2,2"));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[]{42}, ArraySortStreamChallenge.sortedNumbersFromText("42"));
            assertArrayEquals(new int[]{42}, ArraySortStreamChallenge.sortedNumbersFromText("   42   "));
        }

        @Test
        void testEmptyAndBlankText() {
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedNumbersFromText(""));
            assertArrayEquals(new int[]{}, ArraySortStreamChallenge.sortedNumbersFromText("    "));
        }

        @Test
        void testNumbersWithManyDigits() {
            assertArrayEquals(new int[]{99, 100, 1000, 123456},
                    ArraySortStreamChallenge.sortedNumbersFromText("123456,100,99,1000"));
        }

        @Test
        void testNegativeNumbersAreSortedAsNumbers() {
            assertArrayEquals(new int[]{-20, -3, 0, 5},
                    ArraySortStreamChallenge.sortedNumbersFromText("5, -3, 0, -20"));
        }
    }
}
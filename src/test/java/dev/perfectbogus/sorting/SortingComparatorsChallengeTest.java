package dev.perfectbogus.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortingComparatorsChallengeTest {

    private static int[][] rows(int[]... rows) {
        return rows;
    }

    private static int[][] copyOf(int[][] rows) {
        int[][] copy = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            copy[i] = rows[i].clone();
        }
        return copy;
    }

    // ==========================================================
    // CHALLENGE 1: sortedAscending
    // ==========================================================
    @Nested
    class SortedAscendingTests {

        @Test
        void testSorts() {
            assertArrayEquals(new int[]{1, 2, 3}, SortingComparatorsChallenge.sortedAscending(new int[]{3, 1, 2}));
        }

        @Test
        void testRepeatedAndNegativeNumbers() {
            assertArrayEquals(new int[]{-1, 5, 5}, SortingComparatorsChallenge.sortedAscending(new int[]{5, 5, -1}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.sortedAscending(new int[]{}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[]{7}, SortingComparatorsChallenge.sortedAscending(new int[]{7}));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[]{1, 2, 3, 4},
                    SortingComparatorsChallenge.sortedAscending(new int[]{1, 2, 3, 4}));
        }

        @Test
        void testDescendingInput() {
            assertArrayEquals(new int[]{1, 2, 3, 4},
                    SortingComparatorsChallenge.sortedAscending(new int[]{4, 3, 2, 1}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                    SortingComparatorsChallenge.sortedAscending(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {3, 1, 2};
            SortingComparatorsChallenge.sortedAscending(original);
            assertArrayEquals(new int[]{3, 1, 2}, original);
        }

        @Test
        void testResultIsANewArray() {
            int[] original = {1, 2, 3};
            assertNotSame(original, SortingComparatorsChallenge.sortedAscending(original));
        }

        @Test
        void testManyNumbers() {
            int[] input = new int[1000];
            int[] expected = new int[1000];
            for (int i = 0; i < 1000; i++) {
                input[i] = 999 - i;
                expected[i] = i;
            }
            assertArrayEquals(expected, SortingComparatorsChallenge.sortedAscending(input));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortedDescending
    // ==========================================================
    @Nested
    class SortedDescendingTests {

        @Test
        void testSorts() {
            assertArrayEquals(new int[]{3, 2, 1}, SortingComparatorsChallenge.sortedDescending(new int[]{3, 1, 2}));
        }

        @Test
        void testRepeatedAndNegativeNumbers() {
            assertArrayEquals(new int[]{5, 5, -1}, SortingComparatorsChallenge.sortedDescending(new int[]{5, -1, 5}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.sortedDescending(new int[]{}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[]{7}, SortingComparatorsChallenge.sortedDescending(new int[]{7}));
        }

        @Test
        void testAlreadySortedInAscendingOrder() {
            assertArrayEquals(new int[]{4, 3, 2, 1},
                    SortingComparatorsChallenge.sortedDescending(new int[]{1, 2, 3, 4}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE},
                    SortingComparatorsChallenge.sortedDescending(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}));
        }

        @Test
        void testExtremeValuesInTheOtherOrder() {
            assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE},
                    SortingComparatorsChallenge.sortedDescending(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        void testBigDifferences() {
            assertArrayEquals(new int[]{2_000_000_000, 0, -2_000_000_000},
                    SortingComparatorsChallenge.sortedDescending(new int[]{0, -2_000_000_000, 2_000_000_000}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {3, 1, 2};
            SortingComparatorsChallenge.sortedDescending(original);
            assertArrayEquals(new int[]{3, 1, 2}, original);
        }

        @Test
        void testResultIsANewArray() {
            int[] original = {3, 2, 1};
            assertNotSame(original, SortingComparatorsChallenge.sortedDescending(original));
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortedByAbsolute
    // ==========================================================
    @Nested
    class SortedByAbsoluteTests {

        @Test
        void testSorts() {
            assertArrayEquals(new int[]{-1, 2, -3, 3},
                    SortingComparatorsChallenge.sortedByAbsolute(new int[]{-3, 2, -1, 3}));
        }

        @Test
        void testEqualAbsoluteValuesKeepTheirOrder() {
            assertArrayEquals(new int[]{3, -3, 3}, SortingComparatorsChallenge.sortedByAbsolute(new int[]{3, -3, 3}));
            assertArrayEquals(new int[]{-3, 3, -3},
                    SortingComparatorsChallenge.sortedByAbsolute(new int[]{-3, 3, -3}));
        }

        @Test
        void testEqualAbsoluteValuesKeepTheirOrderAmongOthers() {
            assertArrayEquals(new int[]{1, -2, 2, -5, 5},
                    SortingComparatorsChallenge.sortedByAbsolute(new int[]{-5, -2, 1, 2, 5}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.sortedByAbsolute(new int[]{}));
        }

        @Test
        void testAllPositive() {
            assertArrayEquals(new int[]{1, 2, 3}, SortingComparatorsChallenge.sortedByAbsolute(new int[]{3, 1, 2}));
        }

        @Test
        void testAllNegative() {
            assertArrayEquals(new int[]{-1, -2, -3},
                    SortingComparatorsChallenge.sortedByAbsolute(new int[]{-3, -1, -2}));
        }

        @Test
        void testNegativeIsNotSmallerJustBecauseItIsNegative() {
            assertArrayEquals(new int[]{1, -2}, SortingComparatorsChallenge.sortedByAbsolute(new int[]{-2, 1}));
        }

        @Test
        void testZero() {
            assertArrayEquals(new int[]{0, -1, 2}, SortingComparatorsChallenge.sortedByAbsolute(new int[]{2, 0, -1}));
        }

        @Test
        void testBigNumbers() {
            assertArrayEquals(new int[]{5, -Integer.MAX_VALUE},
                    SortingComparatorsChallenge.sortedByAbsolute(new int[]{-Integer.MAX_VALUE, 5}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {-3, 2, -1};
            SortingComparatorsChallenge.sortedByAbsolute(original);
            assertArrayEquals(new int[]{-3, 2, -1}, original);
        }
    }

    // ==========================================================
    // CHALLENGE 4: evensFirst
    // ==========================================================
    @Nested
    class EvensFirstTests {

        @Test
        void testEvensThenOdds() {
            assertArrayEquals(new int[]{2, 4, 1, 5, 7},
                    SortingComparatorsChallenge.evensFirst(new int[]{5, 2, 7, 4, 1}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-2, 0, -3, 3},
                    SortingComparatorsChallenge.evensFirst(new int[]{-3, -2, 0, 3}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.evensFirst(new int[]{}));
        }

        @Test
        void testOnlyEvenNumbers() {
            assertArrayEquals(new int[]{2, 4, 6}, SortingComparatorsChallenge.evensFirst(new int[]{6, 2, 4}));
        }

        @Test
        void testOnlyOddNumbers() {
            assertArrayEquals(new int[]{1, 3, 5}, SortingComparatorsChallenge.evensFirst(new int[]{5, 1, 3}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[]{2, 2, 1, 1}, SortingComparatorsChallenge.evensFirst(new int[]{1, 2, 1, 2}));
        }

        @Test
        void testOddNegativeNumbersAreOdd() {
            assertArrayEquals(new int[]{4, -5, -1}, SortingComparatorsChallenge.evensFirst(new int[]{-1, -5, 4}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                    SortingComparatorsChallenge.evensFirst(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}));
        }

        @Test
        void testEachGroupIsSortedNotKeptInTheInputOrder() {
            assertArrayEquals(new int[]{2, 8, 1, 9}, SortingComparatorsChallenge.evensFirst(new int[]{9, 8, 1, 2}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {5, 2, 7};
            SortingComparatorsChallenge.evensFirst(original);
            assertArrayEquals(new int[]{5, 2, 7}, original);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortedByLastDigit
    // ==========================================================
    @Nested
    class SortedByLastDigitTests {

        @Test
        void testSortsByLastDigit() {
            assertArrayEquals(new int[]{40, 21, 32, 13},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{13, 21, 32, 40}));
        }

        @Test
        void testSameLastDigitSmallerNumberFirst() {
            assertArrayEquals(new int[]{3, 13, 23},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{23, 13, 3}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-2, 2, -13},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{-13, 2, -2}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.sortedByLastDigit(new int[]{}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[]{15}, SortingComparatorsChallenge.sortedByLastDigit(new int[]{15}));
        }

        @Test
        void testZeroLastDigitComesFirst() {
            assertArrayEquals(new int[]{10, 100, 1},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{1, 100, 10}));
        }

        @Test
        void testNegativeNumbersWithTheSameLastDigit() {
            assertArrayEquals(new int[]{-25, -5, 15},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{15, -5, -25}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[]{11, 11, 2},
                    SortingComparatorsChallenge.sortedByLastDigit(new int[]{2, 11, 11}));
        }

        @Test
        void testLastDigitOfANegativeNumberIsNotNegative() {
            assertArrayEquals(new int[]{1, -9}, SortingComparatorsChallenge.sortedByLastDigit(new int[]{-9, 1}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {13, 21, 32};
            SortingComparatorsChallenge.sortedByLastDigit(original);
            assertArrayEquals(new int[]{13, 21, 32}, original);
        }
    }

    // ==========================================================
    // CHALLENGE 6: byFirstElement
    // ==========================================================
    @Nested
    class ByFirstElementTests {

        @Test
        void testSmallerFirstElement() {
            assertTrue(SortingComparatorsChallenge.byFirstElement().compare(new int[]{1, 9}, new int[]{2, 0}) < 0);
        }

        @Test
        void testGreaterFirstElement() {
            assertTrue(SortingComparatorsChallenge.byFirstElement().compare(new int[]{5}, new int[]{3, 8}) > 0);
        }

        @Test
        void testEqualFirstElementGivesZero() {
            assertEquals(0, SortingComparatorsChallenge.byFirstElement().compare(new int[]{4, 1}, new int[]{4, 7}));
        }

        @Test
        void testOnlyTheFirstElementCounts() {
            assertEquals(0, SortingComparatorsChallenge.byFirstElement().compare(new int[]{4, 100},
                    new int[]{4, -100}));
        }

        @Test
        void testNegativeNumbers() {
            assertTrue(SortingComparatorsChallenge.byFirstElement().compare(new int[]{-5}, new int[]{-2}) < 0);
            assertTrue(SortingComparatorsChallenge.byFirstElement().compare(new int[]{-2}, new int[]{-5}) > 0);
        }

        @Test
        void testExtremeValuesDoNotOverflow() {
            Comparator<int[]> comparator = SortingComparatorsChallenge.byFirstElement();
            assertTrue(comparator.compare(new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}) < 0);
            assertTrue(comparator.compare(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MIN_VALUE}) > 0);
        }

        @Test
        void testBigDifferencesDoNotOverflow() {
            Comparator<int[]> comparator = SortingComparatorsChallenge.byFirstElement();
            assertTrue(comparator.compare(new int[]{-2_000_000_000}, new int[]{2_000_000_000}) < 0);
            assertTrue(comparator.compare(new int[]{2_000_000_000}, new int[]{-2_000_000_000}) > 0);
        }

        @Test
        void testSameArray() {
            int[] array = {3, 3};
            assertEquals(0, SortingComparatorsChallenge.byFirstElement().compare(array, array));
        }

        @Test
        void testWorksToSortAnArrayOfArrays() {
            int[][] data = {{3, 0}, {1, 0}, {2, 0}};
            java.util.Arrays.sort(data, SortingComparatorsChallenge.byFirstElement());
            assertArrayEquals(rows(new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0}), data);
        }

        @Test
        void testSortingIsStableWhenFirstElementsAreEqual() {
            int[][] data = {{2, 9}, {1, 5}, {2, 1}, {1, 7}};
            java.util.Arrays.sort(data, SortingComparatorsChallenge.byFirstElement());
            assertArrayEquals(rows(new int[]{1, 5}, new int[]{1, 7}, new int[]{2, 9}, new int[]{2, 1}), data);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortRowsBySum
    // ==========================================================
    @Nested
    class SortRowsBySumTests {

        @Test
        void testSortsBySumDescending() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{1, 1}, new int[]{5},
                    new int[]{2, 2, 2}));
            assertArrayEquals(rows(new int[]{2, 2, 2}, new int[]{5}, new int[]{1, 1}), result);
        }

        @Test
        void testEqualSumsKeepTheirOrder() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{1, 2}, new int[]{3},
                    new int[]{0, 3}));
            assertArrayEquals(rows(new int[]{1, 2}, new int[]{3}, new int[]{0, 3}), result);
        }

        @Test
        void testEqualSumsKeepTheirOrderInTheOtherDirection() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{0, 3}, new int[]{3},
                    new int[]{1, 2}));
            assertArrayEquals(rows(new int[]{0, 3}, new int[]{3}, new int[]{1, 2}), result);
        }

        @Test
        void testExample2() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{3}, new int[]{1, 2},
                    new int[]{0}));
            assertArrayEquals(rows(new int[]{3}, new int[]{1, 2}, new int[]{0}), result);
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(rows(), SortingComparatorsChallenge.sortRowsBySum(rows()));
        }

        @Test
        void testEmptyRowsHaveSumZero() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{}, new int[]{1}, new int[]{-1}));
            assertArrayEquals(rows(new int[]{1}, new int[]{}, new int[]{-1}), result);
        }

        @Test
        void testNegativeSums() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{-5}, new int[]{-1, -1},
                    new int[]{-3}));
            assertArrayEquals(rows(new int[]{-1, -1}, new int[]{-3}, new int[]{-5}), result);
        }

        @Test
        void testGreatestSumIsNotTheLongestRow() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{1, 1, 1, 1}, new int[]{10}));
            assertArrayEquals(rows(new int[]{10}, new int[]{1, 1, 1, 1}), result);
        }

        @Test
        void testGreatestSumIsNotTheGreatestFirstElement() {
            int[][] result = SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{9, -9}, new int[]{1, 1}));
            assertArrayEquals(rows(new int[]{1, 1}, new int[]{9, -9}), result);
        }

        @Test
        void testSingleRow() {
            assertArrayEquals(rows(new int[]{4, 5}), SortingComparatorsChallenge.sortRowsBySum(rows(new int[]{4, 5})));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[][] original = rows(new int[]{1}, new int[]{5}, new int[]{3});
            int[][] copy = copyOf(original);
            SortingComparatorsChallenge.sortRowsBySum(original);
            assertArrayEquals(copy, original);
        }

        @Test
        void testResultIsANewArray() {
            int[][] original = rows(new int[]{1}, new int[]{5});
            assertNotSame(original, SortingComparatorsChallenge.sortRowsBySum(original));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortRowsByLengthThenFirst
    // ==========================================================
    @Nested
    class SortRowsByLengthThenFirstTests {

        @Test
        void testSortsByLengthThenFirst() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(
                    rows(new int[]{5, 5}, new int[]{9}, new int[]{1, 1}, new int[]{2}));
            assertArrayEquals(rows(new int[]{2}, new int[]{9}, new int[]{1, 1}, new int[]{5, 5}), result);
        }

        @Test
        void testEqualLengthAndFirstKeepTheirOrder() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows(new int[]{1, 2},
                    new int[]{1, 1}));
            assertArrayEquals(rows(new int[]{1, 2}, new int[]{1, 1}), result);
        }

        @Test
        void testEqualLengthAndFirstKeepTheirOrderInTheOtherDirection() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows(new int[]{1, 1},
                    new int[]{1, 2}));
            assertArrayEquals(rows(new int[]{1, 1}, new int[]{1, 2}), result);
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(rows(), SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows()));
        }

        @Test
        void testLengthWinsOverTheFirstElement() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows(new int[]{1, 1},
                    new int[]{99}));
            assertArrayEquals(rows(new int[]{99}, new int[]{1, 1}), result);
        }

        @Test
        void testNegativeFirstElements() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(
                    rows(new int[]{-1}, new int[]{-5}, new int[]{0}));
            assertArrayEquals(rows(new int[]{-5}, new int[]{-1}, new int[]{0}), result);
        }

        @Test
        void testExtremeFirstElementsDoNotOverflow() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(
                    rows(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MIN_VALUE}));
            assertArrayEquals(rows(new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}), result);
        }

        @Test
        void testSeveralLengths() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows(
                    new int[]{3, 3, 3}, new int[]{7}, new int[]{1, 1, 1}, new int[]{2, 0}, new int[]{1, 5}));
            assertArrayEquals(rows(new int[]{7}, new int[]{1, 5}, new int[]{2, 0},
                    new int[]{1, 1, 1}, new int[]{3, 3, 3}), result);
        }

        @Test
        void testOnlyTheFirstElementDecidesTheTie() {
            int[][] result = SortingComparatorsChallenge.sortRowsByLengthThenFirst(rows(new int[]{2, 0},
                    new int[]{1, 9}));
            assertArrayEquals(rows(new int[]{1, 9}, new int[]{2, 0}), result);
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[][] original = rows(new int[]{5, 5}, new int[]{9}, new int[]{2});
            int[][] copy = copyOf(original);
            SortingComparatorsChallenge.sortRowsByLengthThenFirst(original);
            assertArrayEquals(copy, original);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortedPairs
    // ==========================================================
    @Nested
    class SortedPairsTests {

        @Test
        void testSorts() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(
                    rows(new int[]{2, 1}, new int[]{1, 5}, new int[]{2, 9}, new int[]{1, 7}));
            assertArrayEquals(rows(new int[]{1, 7}, new int[]{1, 5}, new int[]{2, 9}, new int[]{2, 1}), result);
        }

        @Test
        void testSinglePair() {
            assertArrayEquals(rows(new int[]{3, 3}), SortingComparatorsChallenge.sortedPairs(rows(new int[]{3, 3})));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(rows(), SortingComparatorsChallenge.sortedPairs(rows()));
        }

        @Test
        void testEqualPairs() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(rows(new int[]{1, 1}, new int[]{1, 1}));
            assertArrayEquals(rows(new int[]{1, 1}, new int[]{1, 1}), result);
        }

        @Test
        void testNegativeNumbers() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(
                    rows(new int[]{0, -1}, new int[]{-3, 2}, new int[]{-3, 5}, new int[]{0, 4}));
            assertArrayEquals(rows(new int[]{-3, 5}, new int[]{-3, 2}, new int[]{0, 4}, new int[]{0, -1}), result);
        }

        @Test
        void testSecondNumberDoesNotChangeTheOrderWhenFirstDiffers() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(rows(new int[]{2, 100}, new int[]{1, -100}));
            assertArrayEquals(rows(new int[]{1, -100}, new int[]{2, 100}), result);
        }

        @Test
        void testExtremeValuesDoNotOverflow() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(rows(
                    new int[]{Integer.MAX_VALUE, 0}, new int[]{Integer.MIN_VALUE, 0}));
            assertArrayEquals(rows(new int[]{Integer.MIN_VALUE, 0}, new int[]{Integer.MAX_VALUE, 0}), result);
        }

        @Test
        void testExtremeSecondValuesDoNotOverflow() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(rows(
                    new int[]{1, Integer.MIN_VALUE}, new int[]{1, Integer.MAX_VALUE}));
            assertArrayEquals(rows(new int[]{1, Integer.MAX_VALUE}, new int[]{1, Integer.MIN_VALUE}), result);
        }

        @Test
        void testBigDifferencesInTheSecondValueDoNotOverflow() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(rows(
                    new int[]{1, -2_000_000_000}, new int[]{1, 2_000_000_000}));
            assertArrayEquals(rows(new int[]{1, 2_000_000_000}, new int[]{1, -2_000_000_000}), result);
        }

        @Test
        void testManyPairsWithTheSameFirstNumber() {
            int[][] result = SortingComparatorsChallenge.sortedPairs(
                    rows(new int[]{4, 2}, new int[]{4, 8}, new int[]{4, 5}));
            assertArrayEquals(rows(new int[]{4, 8}, new int[]{4, 5}, new int[]{4, 2}), result);
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[][] original = rows(new int[]{2, 1}, new int[]{1, 5});
            int[][] copy = copyOf(original);
            SortingComparatorsChallenge.sortedPairs(original);
            assertArrayEquals(copy, original);
        }
    }

    // ==========================================================
    // CHALLENGE 10: indicesSortedByValue
    // ==========================================================
    @Nested
    class IndicesSortedByValueTests {

        @Test
        void testSorts() {
            assertArrayEquals(new int[]{1, 2, 0},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{30, 10, 20}));
        }

        @Test
        void testEqualValuesSmallerPositionFirst() {
            assertArrayEquals(new int[]{2, 0, 1}, SortingComparatorsChallenge.indicesSortedByValue(new int[]{5, 5, 1}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new int[]{}, SortingComparatorsChallenge.indicesSortedByValue(new int[]{}));
        }

        @Test
        void testSingleValue() {
            assertArrayEquals(new int[]{0}, SortingComparatorsChallenge.indicesSortedByValue(new int[]{42}));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[]{0, 1, 2}, SortingComparatorsChallenge.indicesSortedByValue(new int[]{1, 2, 3}));
        }

        @Test
        void testDescendingValues() {
            assertArrayEquals(new int[]{2, 1, 0}, SortingComparatorsChallenge.indicesSortedByValue(new int[]{3, 2, 1}));
        }

        @Test
        void testAllEqualValues() {
            assertArrayEquals(new int[]{0, 1, 2, 3},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{7, 7, 7, 7}));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[]{2, 0, 1},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{-1, 0, -9}));
        }

        @Test
        void testExtremeValuesDoNotOverflow() {
            assertArrayEquals(new int[]{1, 0},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
            assertArrayEquals(new int[]{0, 1},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
        }

        @Test
        void testTheResultAreThePositionsNotTheValues() {
            assertArrayEquals(new int[]{3, 0, 2, 1},
                    SortingComparatorsChallenge.indicesSortedByValue(new int[]{20, 40, 30, 10}));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            int[] original = {30, 10, 20};
            SortingComparatorsChallenge.indicesSortedByValue(original);
            assertArrayEquals(new int[]{30, 10, 20}, original);
        }
    }
}
package dev.perfectbogus.two.pointers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointersTest {

    // -------------------------------------------------------------------------
    // Task 1 — Two sum on sorted array
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - twoSum")
    class Task1 {

        @Test
        @DisplayName("Should return correct indices for basic case")
        void shouldReturnCorrectIndices() {
            assertArrayEquals(new int[]{1, 3},
                    TwoPointers.twoSum(new int[]{1, 2, 3, 4, 6}, 6));
        }

        @Test
        @DisplayName("Should return first and last index when they form the pair")
        void shouldReturnFirstAndLast() {
            assertArrayEquals(new int[]{0, 4},
                    TwoPointers.twoSum(new int[]{1, 2, 3, 4, 5}, 6));
        }

        @Test
        @DisplayName("Should handle pair of adjacent elements")
        void shouldHandleAdjacentElements() {
            assertArrayEquals(new int[]{0, 1},
                    TwoPointers.twoSum(new int[]{1, 3, 5, 7}, 4));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void shouldHandleNegativeNumbers() {
            assertArrayEquals(new int[]{0, 3},
                    TwoPointers.twoSum(new int[]{-3, -1, 0, 2, 4}, -1));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Valid palindrome
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - isPalindrome")
    class Task2 {

        @Test
        @DisplayName("Should return true for classic palindrome with spaces and punctuation")
        void shouldReturnTrueForClassicPalindrome() {
            assertTrue(TwoPointers.isPalindrome("A man, a plan, a canal: Panama"));
        }

        @Test
        @DisplayName("Should return false for non-palindrome")
        void shouldReturnFalseForNonPalindrome() {
            assertFalse(TwoPointers.isPalindrome("race a car"));
        }

        @Test
        @DisplayName("Should return true for empty string")
        void shouldReturnTrueForEmpty() {
            assertTrue(TwoPointers.isPalindrome(""));
        }

        @Test
        @DisplayName("Should return true for single character")
        void shouldReturnTrueForSingleChar() {
            assertTrue(TwoPointers.isPalindrome("a"));
        }

        @Test
        @DisplayName("Should be case insensitive")
        void shouldBeCaseInsensitive() {
            assertTrue(TwoPointers.isPalindrome("Racecar"));
        }

        @Test
        @DisplayName("Should handle string with only non-alphanumeric characters")
        void shouldHandleOnlySpecialChars() {
            assertTrue(TwoPointers.isPalindrome(" "));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Container with most water
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - maxWaterContainer")
    class Task3 {

        @Test
        @DisplayName("Should return correct max water for classic example")
        void shouldReturnCorrectMaxWater() {
            assertEquals(49,
                    TwoPointers.maxWaterContainer(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        }

        @Test
        @DisplayName("Should return correct result for two elements")
        void shouldHandleTwoElements() {
            assertEquals(1,
                    TwoPointers.maxWaterContainer(new int[]{1, 1}));
        }

        @Test
        @DisplayName("Should handle increasing heights")
        void shouldHandleIncreasingHeights() {
            assertEquals(6,
                    TwoPointers.maxWaterContainer(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Should handle equal heights")
        void shouldHandleEqualHeights() {
            assertEquals(12,
                    TwoPointers.maxWaterContainer(new int[]{4, 4, 4, 4}));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Three sum
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - threeSum")
    class Task4 {

        @Test
        @DisplayName("Should return all unique triplets summing to zero")
        void shouldReturnAllUniqueTriplets() {
            var result = TwoPointers.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
            assertEquals(2, result.size());
            assertTrue(result.contains(List.of(-1, -1, 2)));
            assertTrue(result.contains(List.of(-1, 0, 1)));
        }

        @Test
        @DisplayName("Should return empty list when no triplet sums to zero")
        void shouldReturnEmptyWhenNoTriplet() {
            var result = TwoPointers.threeSum(new int[]{1, 2, 3});
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return empty list for array with fewer than 3 elements")
        void shouldReturnEmptyForSmallArray() {
            assertTrue(TwoPointers.threeSum(new int[]{0, 1}).isEmpty());
        }

        @Test
        @DisplayName("Should handle array of all zeros")
        void shouldHandleAllZeros() {
            var result = TwoPointers.threeSum(new int[]{0, 0, 0});
            assertEquals(1, result.size());
            assertTrue(result.contains(List.of(0, 0, 0)));
        }

        @Test
        @DisplayName("Should not contain duplicate triplets")
        void shouldNotContainDuplicates() {
            var result = TwoPointers.threeSum(new int[]{-2, 0, 0, 2, 2});
            assertEquals(1, result.size());
            assertTrue(result.contains(List.of(-2, 0, 2)));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Remove duplicates from sorted array
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - removeDuplicates")
    class Task5 {

        @Test
        @DisplayName("Should return count of unique elements")
        void shouldReturnUniqueCount() {
            int[] arr = {1, 1, 2, 3, 3, 4};
            assertEquals(4, TwoPointers.removeDuplicates(arr));
        }

        @Test
        @DisplayName("Should place unique elements at the front of the array")
        void shouldPlaceUniquesAtFront() {
            int[] arr = {1, 1, 2, 3, 3, 4};
            int k = TwoPointers.removeDuplicates(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4},
                    java.util.Arrays.copyOf(arr, k));
        }

        @Test
        @DisplayName("Should return 1 for array of all same elements")
        void shouldReturnOneForAllSame() {
            int[] arr = {5, 5, 5, 5};
            assertEquals(1, TwoPointers.removeDuplicates(arr));
        }

        @Test
        @DisplayName("Should return array length when no duplicates exist")
        void shouldReturnLengthForNoDuplicates() {
            int[] arr = {1, 2, 3, 4};
            assertEquals(4, TwoPointers.removeDuplicates(arr));
        }

        @Test
        @DisplayName("Should handle single element array")
        void shouldHandleSingleElement() {
            int[] arr = {7};
            assertEquals(1, TwoPointers.removeDuplicates(arr));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Move zeros to end
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - moveZeros")
    class Task6 {

        @Test
        @DisplayName("Should move zeros to the end")
        void shouldMoveZerosToEnd() {
            int[] arr = {0, 1, 0, 3, 12};
            TwoPointers.moveZeros(arr);
            assertArrayEquals(new int[]{1, 3, 12, 0, 0}, arr);
        }

        @Test
        @DisplayName("Should preserve relative order of non-zero elements")
        void shouldPreserveOrder() {
            int[] arr = {4, 0, 2, 0, 7, 0, 1};
            TwoPointers.moveZeros(arr);
            assertArrayEquals(new int[]{4, 2, 7, 1, 0, 0, 0}, arr);
        }

        @Test
        @DisplayName("Should do nothing when no zeros present")
        void shouldDoNothingForNoZeros() {
            int[] arr = {1, 2, 3};
            TwoPointers.moveZeros(arr);
            assertArrayEquals(new int[]{1, 2, 3}, arr);
        }

        @Test
        @DisplayName("Should handle array of all zeros")
        void shouldHandleAllZeros() {
            int[] arr = {0, 0, 0};
            TwoPointers.moveZeros(arr);
            assertArrayEquals(new int[]{0, 0, 0}, arr);
        }

        @Test
        @DisplayName("Should handle zeros only at the start")
        void shouldHandleLeadingZeros() {
            int[] arr = {0, 0, 1, 2};
            TwoPointers.moveZeros(arr);
            assertArrayEquals(new int[]{1, 2, 0, 0}, arr);
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Squares of sorted array
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - sortedSquares")
    class Task7 {

        @Test
        @DisplayName("Should return sorted squares for array with negatives")
        void shouldReturnSortedSquares() {
            assertArrayEquals(new int[]{0, 1, 9, 16, 100},
                    TwoPointers.sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        }

        @Test
        @DisplayName("Should handle array with all negatives")
        void shouldHandleAllNegatives() {
            assertArrayEquals(new int[]{1, 4, 9, 16},
                    TwoPointers.sortedSquares(new int[]{-4, -3, -2, -1}));
        }

        @Test
        @DisplayName("Should handle array with all positives")
        void shouldHandleAllPositives() {
            assertArrayEquals(new int[]{1, 4, 9, 16},
                    TwoPointers.sortedSquares(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Should handle single element")
        void shouldHandleSingleElement() {
            assertArrayEquals(new int[]{9},
                    TwoPointers.sortedSquares(new int[]{-3}));
        }

        @Test
        @DisplayName("Should handle array with symmetric negatives and positives")
        void shouldHandleSymmetric() {
            assertArrayEquals(new int[]{1, 1, 4, 4},
                    TwoPointers.sortedSquares(new int[]{-2, -1, 1, 2}));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Four sum
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - fourSum")
    class Task8 {

        @Test
        @DisplayName("Should return all unique quadruplets summing to target")
        void shouldReturnAllUniqueQuadruplets() {
            var result = TwoPointers.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
            assertEquals(3, result.size());
            assertTrue(result.contains(List.of(-2, -1, 1, 2)));
            assertTrue(result.contains(List.of(-2, 0, 0, 2)));
            assertTrue(result.contains(List.of(-1, 0, 0, 1)));
        }

        @Test
        @DisplayName("Should return empty list when no quadruplet sums to target")
        void shouldReturnEmptyWhenNone() {
            var result = TwoPointers.fourSum(new int[]{1, 2, 3, 4}, 100);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should not contain duplicate quadruplets")
        void shouldNotContainDuplicates() {
            var result = TwoPointers.fourSum(new int[]{2, 2, 2, 2, 2}, 8);
            assertEquals(1, result.size());
            assertTrue(result.contains(List.of(2, 2, 2, 2)));
        }

        @Test
        @DisplayName("Should handle negative target")
        void shouldHandleNegativeTarget() {
            var result = TwoPointers.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, -4);
            assertTrue(result.contains(List.of(-3, -2, 0, 1)));
            assertTrue(result.contains(List.of(-3, -1, -1, 1)));
        }

        @Test
        @DisplayName("Should return empty for array with fewer than 4 elements")
        void shouldReturnEmptyForSmallArray() {
            assertTrue(TwoPointers.fourSum(new int[]{1, 2, 3}, 6).isEmpty());
        }
    }
}
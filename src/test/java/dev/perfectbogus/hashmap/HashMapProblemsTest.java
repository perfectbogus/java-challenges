package dev.perfectbogus.hashmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashMapProblemsTest {

    // -------------------------------------------------------------------------
    // Task 1 — Contains duplicate
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - containsDuplicate")
    class Task1 {

        @Test
        @DisplayName("Should return true when duplicate exists")
        void shouldReturnTrueForDuplicate() {
            assertTrue(HashMapProblems.containsDuplicate(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("Should return false when all values are distinct")
        void shouldReturnFalseForDistinct() {
            assertFalse(HashMapProblems.containsDuplicate(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Should return false for single element")
        void shouldReturnFalseForSingle() {
            assertFalse(HashMapProblems.containsDuplicate(new int[]{1}));
        }

        @Test
        @DisplayName("Should return false for empty array")
        void shouldReturnFalseForEmpty() {
            assertFalse(HashMapProblems.containsDuplicate(new int[]{}));
        }

        @Test
        @DisplayName("Should handle duplicate at the end")
        void shouldHandleDuplicateAtEnd() {
            assertTrue(HashMapProblems.containsDuplicate(new int[]{1, 2, 3, 4, 4}));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Longest consecutive sequence
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - longestConsecutiveSequence")
    class Task2 {

        @Test
        @DisplayName("Should return length of longest consecutive sequence")
        void shouldReturnLongestSequence() {
            assertEquals(4,
                    HashMapProblems.longestConsecutiveSequence(new int[]{100, 4, 200, 1, 3, 2}));
        }

        @Test
        @DisplayName("Should handle already sorted array")
        void shouldHandleSortedArray() {
            assertEquals(4,
                    HashMapProblems.longestConsecutiveSequence(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Should return 1 for array with no consecutive elements")
        void shouldReturnOneForNoConsecutive() {
            assertEquals(1,
                    HashMapProblems.longestConsecutiveSequence(new int[]{1, 10, 100}));
        }

        @Test
        @DisplayName("Should handle array with duplicates")
        void shouldHandleDuplicates() {
            assertEquals(3,
                    HashMapProblems.longestConsecutiveSequence(new int[]{1, 2, 2, 3}));
        }

        @Test
        @DisplayName("Should return 0 for empty array")
        void shouldReturnZeroForEmpty() {
            assertEquals(0,
                    HashMapProblems.longestConsecutiveSequence(new int[]{}));
        }

        @Test
        @DisplayName("Should handle longer sequence in middle")
        void shouldHandleLongerSequenceInMiddle() {
            assertEquals(9,
                    HashMapProblems.longestConsecutiveSequence(
                            new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — First non-repeating character
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - firstNonRepeatingChar")
    class Task3 {

        @Test
        @DisplayName("Should return index of first unique character")
        void shouldReturnFirstUniqueIndex() {
            assertEquals(0, HashMapProblems.firstNonRepeatingChar("leetcode"));
        }

        @Test
        @DisplayName("Should return correct index when first char repeats")
        void shouldReturnCorrectIndexWhenFirstRepeats() {
            assertEquals(2, HashMapProblems.firstNonRepeatingChar("loveleetcode"));
        }

        @Test
        @DisplayName("Should return -1 when all characters repeat")
        void shouldReturnNegativeOneForAllRepeating() {
            assertEquals(-1, HashMapProblems.firstNonRepeatingChar("aabb"));
        }

        @Test
        @DisplayName("Should return 0 for single character string")
        void shouldReturnZeroForSingleChar() {
            assertEquals(0, HashMapProblems.firstNonRepeatingChar("z"));
        }

        @Test
        @DisplayName("Should return -1 for empty string")
        void shouldReturnNegativeOneForEmpty() {
            assertEquals(-1, HashMapProblems.firstNonRepeatingChar(""));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Valid anagram
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - isAnagram")
    class Task4 {

        @Test
        @DisplayName("Should return true for valid anagram")
        void shouldReturnTrueForAnagram() {
            assertTrue(HashMapProblems.isAnagram("anagram", "nagaram"));
        }

        @Test
        @DisplayName("Should return false for non-anagram")
        void shouldReturnFalseForNonAnagram() {
            assertFalse(HashMapProblems.isAnagram("rat", "car"));
        }

        @Test
        @DisplayName("Should return false for different lengths")
        void shouldReturnFalseForDifferentLengths() {
            assertFalse(HashMapProblems.isAnagram("ab", "abc"));
        }

        @Test
        @DisplayName("Should return true for identical strings")
        void shouldReturnTrueForIdentical() {
            assertTrue(HashMapProblems.isAnagram("abc", "abc"));
        }

        @Test
        @DisplayName("Should return true for single character strings")
        void shouldHandleSingleChar() {
            assertTrue(HashMapProblems.isAnagram("a", "a"));
            assertFalse(HashMapProblems.isAnagram("a", "b"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Top K frequent elements
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - topKFrequent")
    class Task5 {

        @Test
        @DisplayName("Should return top k frequent elements")
        void shouldReturnTopKFrequent() {
            var result = HashMapProblems.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
            assertEquals(2, result.size());
            assertTrue(result.contains(1));
            assertTrue(result.contains(2));
        }

        @Test
        @DisplayName("Should return single element for k=1")
        void shouldReturnSingleForK1() {
            var result = HashMapProblems.topKFrequent(new int[]{1}, 1);
            assertEquals(1, result.size());
            assertTrue(result.contains(1));
        }

        @Test
        @DisplayName("Should return all elements when k equals distinct count")
        void shouldReturnAllForKEqualsDistinct() {
            var result = HashMapProblems.topKFrequent(new int[]{1, 2, 3}, 3);
            assertEquals(3, result.size());
            assertTrue(result.containsAll(List.of(1, 2, 3)));
        }

        @Test
        @DisplayName("Should return the most frequent element first for k=1")
        void shouldReturnMostFrequentForK1() {
            var result = HashMapProblems.topKFrequent(new int[]{1, 1, 2, 2, 2, 3}, 1);
            assertEquals(1, result.size());
            assertTrue(result.contains(2));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Two sum unsorted
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - twoSum")
    class Task6 {

        @Test
        @DisplayName("Should return correct indices for basic case")
        void shouldReturnCorrectIndices() {
            assertArrayEquals(new int[]{0, 1},
                    HashMapProblems.twoSum(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        @DisplayName("Should handle pair not at the start")
        void shouldHandlePairNotAtStart() {
            assertArrayEquals(new int[]{1, 2},
                    HashMapProblems.twoSum(new int[]{3, 2, 4}, 6));
        }

        @Test
        @DisplayName("Should handle same element used only once")
        void shouldHandleSameValuePair() {
            assertArrayEquals(new int[]{0, 1},
                    HashMapProblems.twoSum(new int[]{3, 3}, 6));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void shouldHandleNegativeNumbers() {
            assertArrayEquals(new int[]{0, 2},
                    HashMapProblems.twoSum(new int[]{-3, 4, 7}, 4));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Subarray sum equals K
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - subarraySumEqualsK")
    class Task7 {

        @Test
        @DisplayName("Should return count of subarrays summing to k")
        void shouldReturnCorrectCount() {
            assertEquals(2, HashMapProblems.subarraySumEqualsK(new int[]{1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Should handle multiple valid subarrays")
        void shouldHandleMultipleSubarrays() {
            assertEquals(2, HashMapProblems.subarraySumEqualsK(new int[]{1, 2, 3}, 3));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void shouldHandleNegativeNumbers() {
            assertEquals(3,
                    HashMapProblems.subarraySumEqualsK(new int[]{1, -1, 1, -1, 1}, 0));
        }

        @Test
        @DisplayName("Should return 0 when no subarray sums to k")
        void shouldReturnZeroWhenNone() {
            assertEquals(0, HashMapProblems.subarraySumEqualsK(new int[]{1, 2, 3}, 7));
        }

        @Test
        @DisplayName("Should handle single element equal to k")
        void shouldHandleSingleElement() {
            assertEquals(1, HashMapProblems.subarraySumEqualsK(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Should handle whole array summing to k")
        void shouldHandleWholeArraySum() {
            assertEquals(1, HashMapProblems.subarraySumEqualsK(new int[]{1, 2, 3}, 6));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Longest subarray with equal 0s and 1s
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - longestEqualZerosAndOnes")
    class Task8 {

        @Test
        @DisplayName("Should return 2 for [0,1]")
        void shouldReturnTwoForBasicCase() {
            assertEquals(2,
                    HashMapProblems.longestEqualZerosAndOnes(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Should return 2 for [0,1,0]")
        void shouldReturnTwoForOddLength() {
            assertEquals(2,
                    HashMapProblems.longestEqualZerosAndOnes(new int[]{0, 1, 0}));
        }

        @Test
        @DisplayName("Should return 6 for longer array")
        void shouldReturnSixForLongerArray() {
            assertEquals(6,
                    HashMapProblems.longestEqualZerosAndOnes(
                            new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
        }

        @Test
        @DisplayName("Should return 0 for all zeros")
        void shouldReturnZeroForAllZeros() {
            assertEquals(0,
                    HashMapProblems.longestEqualZerosAndOnes(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Should return full length when array is balanced")
        void shouldReturnFullLengthForBalanced() {
            assertEquals(4,
                    HashMapProblems.longestEqualZerosAndOnes(new int[]{0, 1, 0, 1}));
        }

        @Test
        @DisplayName("Should return 0 for single element")
        void shouldReturnZeroForSingle() {
            assertEquals(0,
                    HashMapProblems.longestEqualZerosAndOnes(new int[]{1}));
        }
    }
}
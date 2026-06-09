package dev.perfectbogus.sliding.window;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    // -------------------------------------------------------------------------
    // Task 1 — Maximum sum subarray of size K
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - maxSumSubarray")
    class Task1 {

        @Test
        @DisplayName("Should return max sum of window size k")
        void shouldReturnMaxSum() {
            assertEquals(9, SlidingWindow.maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3));
        }

        @Test
        @DisplayName("Should handle window at the end of array")
        void shouldHandleWindowAtEnd() {
            assertEquals(7, SlidingWindow.maxSumSubarray(new int[]{1, 2, 3, 4}, 2));
        }

        @Test
        @DisplayName("Should handle k equal to array length")
        void shouldHandleKEqualToLength() {
            assertEquals(10, SlidingWindow.maxSumSubarray(new int[]{1, 2, 3, 4}, 4));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void shouldHandleNegativeNumbers() {
            assertEquals(5, SlidingWindow.maxSumSubarray(new int[]{-1, 2, 3, 1, -2}, 2));
        }

        @Test
        @DisplayName("Should return 0 when array length < k")
        void shouldReturnZeroWhenArrayTooSmall() {
            assertEquals(0, SlidingWindow.maxSumSubarray(new int[]{1, 2}, 5));
        }

        @Test
        @DisplayName("Should return 0 for empty array")
        void shouldReturnZeroForEmpty() {
            assertEquals(0, SlidingWindow.maxSumSubarray(new int[]{}, 3));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Average of every subarray of size K
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - averageSubarrays")
    class Task2 {

        @Test
        @DisplayName("Should return correct averages for each window")
        void shouldReturnCorrectAverages() {
            double[] result = SlidingWindow.averageSubarrays(
                    new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5);
            assertArrayEquals(new double[]{2.2, 2.8, 2.4, 3.6, 2.8}, result, 0.01);
        }

        @Test
        @DisplayName("Should return single element when k equals array length")
        void shouldReturnSingleAverage() {
            double[] result = SlidingWindow.averageSubarrays(new int[]{1, 2, 3}, 3);
            assertArrayEquals(new double[]{2.0}, result, 0.01);
        }

        @Test
        @DisplayName("Result length should be array.length - k + 1")
        void shouldReturnCorrectLength() {
            double[] result = SlidingWindow.averageSubarrays(new int[]{1, 2, 3, 4, 5}, 3);
            assertEquals(3, result.length);
        }

        @Test
        @DisplayName("Should return empty array when array length < k")
        void shouldReturnEmptyWhenTooSmall() {
            double[] result = SlidingWindow.averageSubarrays(new int[]{1, 2}, 5);
            assertEquals(0, result.length);
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — First negative in every window of size K
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - firstNegativeInWindow")
    class Task3 {

        @Test
        @DisplayName("Should return first negative or 0 per window")
        void shouldReturnFirstNegativePerWindow() {
            assertEquals(List.of(-8, 0, -6, -6),
                    SlidingWindow.firstNegativeInWindow(new int[]{-8, 2, 3, -6, 10}, 2));
        }

        @Test
        @DisplayName("Should return 0 for all-positive windows")
        void shouldReturnZeroForAllPositive() {
            assertEquals(List.of(0, 0, 0),
                    SlidingWindow.firstNegativeInWindow(new int[]{1, 2, 3, 4}, 2));
        }

        @Test
        @DisplayName("Should handle multiple negatives in window — return first")
        void shouldReturnFirstOfMultipleNegatives() {
            assertEquals(List.of(-3, -5),
                    SlidingWindow.firstNegativeInWindow(new int[]{-3, -5, 1}, 2));
        }

        @Test
        @DisplayName("Should handle k equal to array length")
        void shouldHandleKEqualToLength() {
            assertEquals(List.of(-1),
                    SlidingWindow.firstNegativeInWindow(new int[]{1, 2, -1, 4}, 4));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Longest subarray with sum <= target
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - longestSubarraySumAtMost")
    class Task4 {

        @Test
        @DisplayName("Should return length of longest valid subarray")
        void shouldReturnLongestSubarray() {
            assertEquals(3, SlidingWindow.longestSubarraySumAtMost(
                    new int[]{1, 2, 3, 4, 5}, 9));
        }

        @Test
        @DisplayName("Should return full array length when total sum <= target")
        void shouldReturnFullLengthWhenAllValid() {
            assertEquals(4, SlidingWindow.longestSubarraySumAtMost(
                    new int[]{1, 1, 1, 1}, 10));
        }

        @Test
        @DisplayName("Should return 1 for single element <= target")
        void shouldReturnOneForSingleElement() {
            assertEquals(1, SlidingWindow.longestSubarraySumAtMost(
                    new int[]{5, 10, 15}, 7));
        }

        @Test
        @DisplayName("Should return 0 when no element fits")
        void shouldReturnZeroWhenNothingFits() {
            assertEquals(0, SlidingWindow.longestSubarraySumAtMost(
                    new int[]{10, 20, 30}, 5));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Longest substring without repeating characters
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - longestSubstringNoRepeat")
    class Task5 {

        @Test
        @DisplayName("Should return correct length for classic example")
        void shouldReturnCorrectLength() {
            assertEquals(3, SlidingWindow.longestSubstringNoRepeat("abcabcbb"));
        }

        @Test
        @DisplayName("Should return 1 for all same characters")
        void shouldReturnOneForAllSame() {
            assertEquals(1, SlidingWindow.longestSubstringNoRepeat("bbbbb"));
        }

        @Test
        @DisplayName("Should handle non-adjacent duplicates")
        void shouldHandleNonAdjacentDuplicates() {
            assertEquals(3, SlidingWindow.longestSubstringNoRepeat("pwwkew"));
        }

        @Test
        @DisplayName("Should return full length for string with no repeats")
        void shouldReturnFullLengthForNoRepeats() {
            assertEquals(5, SlidingWindow.longestSubstringNoRepeat("abcde"));
        }

        @Test
        @DisplayName("Should return 0 for empty string")
        void shouldReturnZeroForEmpty() {
            assertEquals(0, SlidingWindow.longestSubstringNoRepeat(""));
        }

        @Test
        @DisplayName("Should handle single character")
        void shouldHandleSingleChar() {
            assertEquals(1, SlidingWindow.longestSubstringNoRepeat("a"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Smallest subarray with sum >= target
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - smallestSubarraySumAtLeast")
    class Task6 {

        @Test
        @DisplayName("Should return smallest subarray length")
        void shouldReturnSmallestLength() {
            assertEquals(2, SlidingWindow.smallestSubarraySumAtLeast(new int[]{2, 1, 5, 2, 3, 2}, 7));
        }

        @Test
        @DisplayName("Should return 1 when single element >= target")
        void shouldReturnOneForLargeElement() {
            assertEquals(1, SlidingWindow.smallestSubarraySumAtLeast(new int[]{2, 1, 5, 2, 8}, 7));
        }

        @Test
        @DisplayName("Should return full length when needed")
        void shouldReturnFullLengthWhenNeeded() {
            assertEquals(4, SlidingWindow.smallestSubarraySumAtLeast(
                    new int[]{3, 4, 1, 1}, 9));
        }

        @Test
        @DisplayName("Should return 0 when no subarray reaches target")
        void shouldReturnZeroWhenImpossible() {
            assertEquals(0, SlidingWindow.smallestSubarraySumAtLeast(
                    new int[]{1, 1, 1}, 10));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Longest substring with at most K distinct characters
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - longestSubstringKDistinct")
    class Task7 {

        @Test
        @DisplayName("Should return correct length for k=2")
        void shouldReturnCorrectLengthForK2() {
            assertEquals(4, SlidingWindow.longestSubstringKDistinct("araaci", 2));
        }

        @Test
        @DisplayName("Should return correct length for k=1")
        void shouldReturnCorrectLengthForK1() {
            assertEquals(2, SlidingWindow.longestSubstringKDistinct("araaci", 1));
        }

        @Test
        @DisplayName("Should return full length when k >= distinct chars in string")
        void shouldReturnFullLengthWhenKIsLarge() {
            assertEquals(6, SlidingWindow.longestSubstringKDistinct("araaci", 10));
        }

        @Test
        @DisplayName("Should return 0 for empty string")
        void shouldReturnZeroForEmpty() {
            assertEquals(0, SlidingWindow.longestSubstringKDistinct("", 2));
        }

        @Test
        @DisplayName("Should handle string with all same characters")
        void shouldHandleAllSameChars() {
            assertEquals(4, SlidingWindow.longestSubstringKDistinct("aaaa", 1));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Maximum vowels in substring of length K
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - maxVowelsInSubstring")
    class Task8 {

        @Test
        @DisplayName("Should return max vowels in any window of size k")
        void shouldReturnMaxVowels() {
            assertEquals(3, SlidingWindow.maxVowelsInSubstring("abciiidef", 3));
        }

        @Test
        @DisplayName("Should return k when all characters are vowels")
        void shouldReturnKForAllVowels() {
            assertEquals(2, SlidingWindow.maxVowelsInSubstring("aeiou", 2));
        }

        @Test
        @DisplayName("Should return 0 when no vowels in string")
        void shouldReturnZeroForNoVowels() {
            assertEquals(0, SlidingWindow.maxVowelsInSubstring("bcdfg", 3));
        }

        @Test
        @DisplayName("Should return 0 when string length < k")
        void shouldReturnZeroWhenStringTooShort() {
            assertEquals(0, SlidingWindow.maxVowelsInSubstring("ab", 5));
        }

        @Test
        @DisplayName("Should handle mixed vowels and consonants")
        void shouldHandleMixed() {
            assertEquals(2, SlidingWindow.maxVowelsInSubstring("leetcode", 3));
            // "lee"=2, "eet"=2, "etc"=1, "tco"=1, "cod"=1, "ode"=2
        }

        @Test
        @DisplayName("Should handle single character window")
        void shouldHandleSingleCharWindow() {
            assertEquals(1, SlidingWindow.maxVowelsInSubstring("aeiou", 1));
        }
    }
}
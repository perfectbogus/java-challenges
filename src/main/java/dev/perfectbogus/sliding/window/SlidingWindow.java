package dev.perfectbogus.sliding.window;

import java.util.*;

public class SlidingWindow {

    // Task 1 — Fixed window
    // Return the maximum sum of any contiguous subarray of exactly k elements.
    // Return 0 if arr.length < k.
    public static int maxSumSubarray(int[] arr, int k) {
        if (arr.length == 0 || arr.length < k) return 0;
        if (k < 1) throw new IllegalArgumentException("K must be positive");

        // TODO: implement
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i];
            windowSum -= arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    // Task 2 — Fixed window
    // Return a double[] where each element is the average of a window of size k.
    // Result length = arr.length - k + 1.
    // Return empty array if arr.length < k.
    public static double[] averageSubarrays(int[] arr, int k) {
        if (arr.length < k) return new double[0];
        if (k < 1) throw new IllegalArgumentException("K must be positive");
        // TODO: implement
        int windowSum = 0;
        double[] result = new double[arr.length - k + 1];

        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        result[0] = (double) windowSum / k;

        for (int i = k; i < arr.length ; i++) {
            windowSum += arr[i];
            windowSum -= arr[i - k];
            result[i - k + 1] = (double) windowSum / k;
        }

        return result;
    }

    // Task 3 — Fixed window
    // Return a List<Integer> where each entry is the first negative number
    // in each window of size k, or 0 if the window has no negatives.
    public static List<Integer> firstNegativeInWindow(int[] arr, int k) {
        // TODO: implement
        if (arr.length < k) return List.of(0);
        if (k < 1) throw new IllegalArgumentException("K must be positive");

        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        for (int right = 0; right < arr.length; right++) {
            if (!deque.isEmpty() && deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }

            if (arr[right] < 0) {
                deque.addLast(right);
            }

            if (right >= k - 1) {
                result.add(deque.isEmpty() ? 0 : arr[deque.peekFirst()]);
            }
        }
        return result;
    }


    // Task 4 — Variable window
    // Return the length of the longest contiguous subarray with sum <= target.
    // All elements are positive integers.
    // Return 0 if no valid subarray exists.
    public static int longestSubarraySumAtMost(int[] arr, int target) {
        // TODO: implement

        if (target < 0) throw new IllegalArgumentException("Target cannot be negative");
        if (arr.length == 0) return 0;

        int left = 0;
        int sum = 0;
        int maxLen = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Task 5 — Variable window
    // Return the length of the longest substring with no repeating characters.
    // Use a HashMap<Character, Integer> to track last seen index of each character.
    // When a duplicate is found, move left to max(left, lastSeen + 1).
    public static int longestSubstringNoRepeat(String s) {
        // TODO: implement
        return 0;
    }

    // Task 6 — Variable window
    // Return the length of the smallest subarray with sum >= target.
    // All elements are positive integers.
    // Return 0 if no such subarray exists.
    public static int smallestSubarraySumAtLeast(int[] arr, int target) {
        // TODO: implement
        return 0;
    }

    // Task 7 — Variable window
    // Return the length of the longest substring with at most k distinct characters.
    // Use a HashMap<Character, Integer> to count character frequencies.
    // Shrink from the left when the number of distinct characters exceeds k.
    public static int longestSubstringKDistinct(String s, int k) {
        // TODO: implement
        return 0;
    }

    // Task 8 — Fixed window on a string
    // Return the maximum number of vowel characters (a,e,i,o,u)
    // in any substring of length exactly k.
    // Return 0 if s.length() < k.
    public static int maxVowelsInSubstring(String s, int k) {
        // TODO: implement
        return 0;
    }
}

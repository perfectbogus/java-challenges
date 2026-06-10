package dev.perfectbogus.two.pointers;

import java.util.List;

public class TwoPointers {

    // Task 1 — Converging pointers
    // Return int[2] with the 0-based indices of two numbers that add up to target.
    // The array is already sorted. Exactly one solution exists.
    public static int[] twoSum(int[] arr, int target) {
        if (arr == null) throw new IllegalArgumentException("Arr cannot be null" );
        if (arr.length == 0) throw new IllegalArgumentException("Arr cannot be empty");
        // TODO: implement
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[0];
    }

    // Task 2 — Converging pointers
    // Return true if the string is a palindrome ignoring non-alphanumeric
    // characters and case. Move left/right skipping non-alphanumeric chars.
    public static boolean isPalindrome(String s) {
        // TODO: implement
        if (s.isBlank()) return true;
        String trimmed = s.replaceAll("\\W", " ").replaceAll("\\s", "").toLowerCase().trim();

        int left = 0;
        int right = trimmed.length() - 1;

        while (left < right) {
            if (trimmed.charAt(left) != trimmed.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Task 3 — Converging pointers
    // Return the maximum water volume between any two lines.
    // Volume = min(height[left], height[right]) * (right - left).
    // Move the pointer with the smaller height inward.
    public static int maxWaterContainer(int[] heights) {
        // TODO: implement
        return 0;
    }

    // Task 4 — Converging pointers
    // Return all unique triplets [a, b, c] from the unsorted array where a+b+c=0.
    // Sort first, fix one element, use two pointers for the pair.
    // Skip duplicate values for all three pointers.
    public static List<List<Integer>> threeSum(int[] arr) {
        // TODO: implement
        return List.of();
    }

    // Task 5 — Same-direction pointers
    // Remove duplicates from a sorted array in-place.
    // Return the count of unique elements.
    // slow tracks the last unique position, fast scans forward.
    public static int removeDuplicates(int[] arr) {
        // TODO: implement
        return 0;
    }

    // Task 6 — Same-direction pointers
    // Move all zeros to the end while preserving order of non-zero elements.
    // In-place using swap: when fast finds non-zero, swap with slow and advance slow.
    public static void moveZeros(int[] arr) {
        // TODO: implement
    }

    // Task 7 — Same-direction pointers (fill from the back)
    // Return a new array of squares of each number in non-decreasing order.
    // Use pointers at both ends — place the larger square at the back of result.
    public static int[] sortedSquares(int[] arr) {
        // TODO: implement
        return new int[0];
    }

    // Task 8 — Combined
    // Return all unique quadruplets [a,b,c,d] where a+b+c+d = target.
    // Sort first, fix two outer elements (skip duplicates for both),
    // then use two pointers for the inner pair (skip duplicates for both).
    public static List<List<Integer>> fourSum(int[] arr, int target) {
        // TODO: implement
        return List.of();
    }
}
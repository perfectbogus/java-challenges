package dev.perfectbogus.hackerrank.rotation;

import java.util.Arrays;

public class RotationSum {

    public static void main(String[] args) {
        int[] nums1 = {5, 8, 3, 11};
        int[] nums2 = {12, 6, 7, 8};

//        int[] result = solve(nums1, nums2);
//        for (int r : result) {
//            System.out.println(r);
//        }

        int[] result = new int[nums1.length];
        for (int shift = 0; shift < nums1.length; shift++) {
            int[] shifted = shiftToRight(nums1, shift);

            int sum = 0;
            for (int i = 0; i < nums1.length; i++) {
                sum += Math.abs(shifted[i] - nums2[i]);
            }
            result[shift] = sum;
        }

        for (int i : result) {
            System.out.println(i);
        }

        Arrays.sort(result);
        for (int i : result) {
            System.out.println(i);
        }

    }

    public static int[] shiftToLeft(int[] nums, int shift) {
        int[] shifted = new int[nums.length];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int index = Math.abs(i - shift) % n;
            shifted[index] = nums[i];
        }

        return shifted;
    }

    public static int[] shiftToRight(int[] nums1, int shift) {
        int[] shifted = new int[nums1.length];
        int n = nums1.length;

        for (int i = 0; i < nums1.length; i++) {
            int index = (i + shift)%n;
            shifted[index] = nums1[i];
        }
        return shifted;
    }

    public static int[] solve(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) throw new IllegalArgumentException("Arrays cannot be null");
        if (nums1.length != nums2.length) throw new IllegalArgumentException("Arrays must be same length");
        if (nums1.length == 0) return new int[0];

        int n = nums1.length;
        int[] results = new int[n];

        for (int shift = 0; shift < n; shift++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int rotatedIndex = (n - shift + i) % n;
                sum += Math.abs(nums1[rotatedIndex] - nums2[i]);
            }
            results[shift] = sum;
        }
        Arrays.sort(results);
        return results;
    }
}

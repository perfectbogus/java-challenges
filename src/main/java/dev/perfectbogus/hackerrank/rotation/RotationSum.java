package dev.perfectbogus.hackerrank.rotation;

import java.util.Arrays;

public class RotationSum {

    public static void main(String[] args) {
        int[] nums1 = {5, 8, 3, 11};
        int[] nums2 = {12, 6, 7, 8};

        int[] result = solve(nums1, nums2);
        for (int r : result) {
            System.out.println(r);
        }
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

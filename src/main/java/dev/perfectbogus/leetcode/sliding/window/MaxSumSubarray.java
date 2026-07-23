package dev.perfectbogus.leetcode.sliding.window;

public class MaxSumSubarray {

    public static int solve(int[] nums, int k) {
        // 1. Validate inputs
        if (nums == null) throw new IllegalArgumentException("Nums is null");
        if (nums.length == 0) return 0;
        if (k < 1) throw new IllegalArgumentException("K must be positive");
        if (k > nums.length) throw new IllegalArgumentException("Nums size is less than k");

        // 2. Calculate sum of first window [0..k)
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int max = sum;
        // 3. Slide window — remove leftmost, add rightmost
        //    track maximum sum seen
        for (int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            max = Math.max(max, sum);
        }

        // 4. Return max sum

        return max; // placeholder
    }
}

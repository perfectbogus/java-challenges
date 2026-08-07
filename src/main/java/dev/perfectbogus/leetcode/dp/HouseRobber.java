package dev.perfectbogus.leetcode.dp;

import java.util.Arrays;

public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(rob(nums));

        System.out.println(bottomUp(nums));

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        System.out.println(recursiveMemo(nums, 0, dp));
    }

    public static int rob(int[] nums) {
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return helper(nums, nums.length - 1, memo);
    }

    public static int helper(int[] nums, int i, int[] memo) {
        if (i < 0) {
            return 0;
        }

        if (memo[i] >= 0) {
            return memo[i];
        }

        int result = Math.max(helper(nums, i - 2, memo) + nums[i], helper(nums, i - 1, memo));
        memo[i] = result;

        return result;
    }

    public static int bottomUp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            memo[i + 1] = Math.max(memo[i], memo[i - 1] + val);
        }

        return memo[nums.length];
    }

    public static int recursiveMemo(int[] nums, int i, int[] dp) {
        if (i >= nums.length) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int steal = nums[i] + recursiveMemo(nums, i + 2, dp);
        int skip = recursiveMemo(nums, i + 1, dp);

        return dp[i] = Math.max(steal, skip);
    }
}

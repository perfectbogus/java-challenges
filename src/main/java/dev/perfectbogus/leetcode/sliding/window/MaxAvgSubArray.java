package dev.perfectbogus.leetcode.sliding.window;

public class MaxAvgSubArray {

    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        System.out.println(maxAvg(nums, 4));
    }

    public static double maxAvg(int[] nums, int k) {
        double sum = 0.0;

        for (int i = 0; i < k ; i++) {
            sum += nums[i];
        }

        double max = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i-k];
            max = Math.max(max, sum);
        }

        return (double) max/k;
    }
}

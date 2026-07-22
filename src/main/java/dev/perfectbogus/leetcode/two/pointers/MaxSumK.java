package dev.perfectbogus.leetcode.two.pointers;

import java.util.Arrays;

public class MaxSumK {

    public static void main(String[] args) {
        int[] nums = {4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4};
        int result = maxOperations(nums, 6);
        System.out.println(result);
    }

    public static int maxOperations(int[] nums, int k) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int count = 0;

        Arrays.sort(nums);

        while (leftIndex < rightIndex) {
            int sum = nums[leftIndex] + nums[rightIndex];
            if (sum == k) {
                count++;
                leftIndex++;
                rightIndex--;
             } else if (sum > k) {
                rightIndex--;
            } else {
                leftIndex++;
            }
        }

        return count;
    }
}

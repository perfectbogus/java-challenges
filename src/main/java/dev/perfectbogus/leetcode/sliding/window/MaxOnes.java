package dev.perfectbogus.leetcode.sliding.window;

public class MaxOnes {

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,1,0,0,0,0,0,0};
        System.out.println(longest(nums, 2));
    }

    public static int longest(int[] nums, int k) {
        int i = 0;
        int j = 0;

        while (j < nums.length) {
            if (nums[j++] == 0) {
                k--;
            }

            if (k < 0) {
                if (nums[i++] == 0) {
                    k++;
                }
            }
        }
        return j - i;
    }
}

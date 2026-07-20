package dev.perfectbogus.leetcode.two.pointers;

public class MoveZeros {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12 };
        moveZeroes(nums);
        for(int n : nums) {
            System.out.printf("%d ,", n);
        }
        System.out.println();




    }

    public static void moveZeroes(int[] nums) {
        int z = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[z];
                nums[z] = temp;
                z++;
            }
        }
    }
}

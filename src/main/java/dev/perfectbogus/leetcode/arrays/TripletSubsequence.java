package dev.perfectbogus.leetcode.arrays;

public class TripletSubsequence {

    public static void main(String[] args) {
        int[] nums = {6, 7, 1, 2};
        System.out.println(increasingTripletTwo(nums));

        System.out.println(increasingTripletThree(nums));
    }

    public static boolean increasingTripletThree(int[] nums) {
        int SMALL = Integer.MAX_VALUE;
        int BIG = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= SMALL) {
                SMALL = n;
            } else if (n <= BIG) {
                BIG = n;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean increasingTripletTwo(int[] nums) {
        int n = nums.length;
        int i = 0;
        boolean found = false;
        while (i < n) {
            int j = i + 1;
            while (j < n) {
                if (nums[i] < nums[j]) {
                    i = j;
                    found = true;
                    break;
                } else {
                    j++;
                }
            }
            if (found) {
                break;
            }
            i++;
        }


        int j = i + 1;
        while (j < n) {
            if (nums[i] < nums[j]) {
                return true;
            } else {
                j++;
            }
        }

        return false;
    }

    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        boolean found = false;
        int i = 0;
        int tmp = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n) {
                if (nums[i] < nums[j]){
                    tmp = j;
                    found = true;
                    break;
                }
                j++;
            }
            i++;
        }

        if (!found) {
            return false;
        }

        int k = tmp;
        while (k < n) {
            int l = k + 1;
            while (l < n) {
                if (nums[k] < nums[l]) {
                    return true;
                }
                l++;
            }
        }
        return false;
    }
}

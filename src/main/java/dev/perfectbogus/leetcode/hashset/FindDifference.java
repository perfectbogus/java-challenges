package dev.perfectbogus.leetcode.hashset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDifference {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {2,4,6};

        List<List<Integer>> result = findDifference(nums1, nums2);

        for (List<Integer> list : result) {
            for (int x : list) {
                System.out.printf("%d ", x);
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        Set<Integer> seenIn1 = new HashSet<>();
        Set<Integer> seenIn2 = new HashSet<>();

        for (int j : nums1) {
            seenIn1.add(j);
        }

        for (int j : nums2) {
            seenIn2.add(j);
        }

        List<Integer> notInNums2 = new ArrayList<>();
        for (int x : seenIn1) {
            if (!seenIn2.contains(x)) {
                notInNums2.add(x);
            }
        }

        List<Integer> notInNums1 = new ArrayList<>();
        for (int x : seenIn2) {
            if (!seenIn1.contains(x)) {
                notInNums1.add(x);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        result.add(notInNums2);
        result.add(notInNums1);

        return result;
    }
}

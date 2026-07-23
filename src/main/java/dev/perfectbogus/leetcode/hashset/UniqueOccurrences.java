package dev.perfectbogus.leetcode.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrences {

    public static void main(String[] args) {
        int[] arr = {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : arr) {
            map.merge(n, 1, Integer::sum);
        }

        Set<Integer> set = new HashSet<>();

        for (int x : map.values()) {
            if (set.contains(x)) {
                return false;
            } else {
                set.add(x);
            }
        }

        return true;
    }

}

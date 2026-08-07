package dev.perfectbogus.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination3 {

    public static void main(String[] args) {
        List<List<Integer>> ans = new ArrayList<>();
        int k = 3;
        int n = 7;
        findCombination(k, 1, n, new ArrayList<>(), ans);

        System.out.println(ans);

        List<List<Integer>> ans2 = new ArrayList<>();
        int k2 = 3;
        int n2 = 9;
        findCombination(k2, 1, n2, new ArrayList<>(), ans2);
        System.out.println(ans2);
    }

    public static void findCombination(int k, int num, int target, ArrayList<Integer> lst, List<List<Integer>> ans) {
        if (target == 0 && k == 0) {
            ans.add(new ArrayList<>(lst));
            return;
        }

        for (int i = num; i < 10; i++) {
            if (i > target || k <= 0) break;

            lst.add(i);
            findCombination(k - 1, i + 1, target - i, lst, ans);
            lst.remove(lst.size() - 1);
        }
    }
}

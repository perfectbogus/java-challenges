package dev.perfectbogus.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {

    public static void main(String[] args) {
        System.out.println(tribonacci(5));
    }

    public static int tribonacci(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        return recursive(n, memo);
    }

    public static int recursive(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = recursive(n - 1, memo) + recursive(n - 2, memo) + recursive(n - 3, memo);

        memo.put(n, result);

        return result;
    }
}

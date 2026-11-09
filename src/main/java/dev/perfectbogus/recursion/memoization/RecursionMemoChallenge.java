package dev.perfectbogus.recursion.memoization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecursionMemoChallenge {

    // CHALLENGE 1
    // Returns the n-th number of the Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, ... where the first two numbers are 0
    // and 1, and each other number is the sum of the two before it. The numbering starts at 0, so fibonacci(0) is 0
    // and fibonacci(1) is 1. n is between 0 and 90.
    // Example: fibonacci(0)  returns 0
    //          fibonacci(1)  returns 1
    //          fibonacci(7)  returns 13
    //          fibonacci(10) returns 55
    public static long fibonacci(int n) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return helperFibonacci(n, memo);
    }

    public static long helperFibonacci(int n, long[] memo) {
        if (n <= 1) return n;

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = helperFibonacci(n - 1, memo) + helperFibonacci(n - 2, memo);

        return memo[n];
    }

    // CHALLENGE 2
    // A staircase has n steps. Each time you can climb 1 step or 2 steps. Returns how many different ways there are
    // to reach the top, where two ways are different if the order of the moves is different. A staircase with 0 steps
    // has 1 way (doing nothing). n is between 0 and 85.
    // Example: climbStairs(0)  returns 1
    //          climbStairs(1)  returns 1
    //          climbStairs(3)  returns 3     (1+1+1, 1+2, 2+1)
    //          climbStairs(5)  returns 8
    public static long climbStairs(int n) {
        long[] memo = new long[n + 2];
        return ways(n, memo);
    }

    public static long ways(int n, long[] memo) {
        if (n <= 1) return 1;

        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = ways(n - 1, memo) + ways(n - 2, memo);
        return memo[n];
    }

    public static long climbStairsSimplified(int n) {
        return helperClimb(n + 1, new HashMap<>());
    }

    private static long helperClimb(int n, HashMap<Integer, Long> memo) {
        if (n < 2) return n;

        Long cached = memo.get(n);
        if (cached != null) {
            return cached;
        }

        long result = helperClimb(n - 1, memo) + helperClimb(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // CHALLENGE 3
    // Returns the n-th number of the Tribonacci sequence: 0, 1, 1, 2, 4, 7, 13, ... where the first three numbers
    // are 0, 1 and 1, and each other number is the sum of the three before it. The numbering starts at 0. n is
    // between 0 and 60.
    // Example: tribonacci(0)  returns 0
    //          tribonacci(2)  returns 1
    //          tribonacci(3)  returns 2
    //          tribonacci(6)  returns 13
    public static long tribonacci(int n) {
        return helperTribonacci(n, new HashMap<>());
    }

    public static long helperTribonacci(int n, Map<Integer, Long> memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        Long cached = memo.get(n);
        if (cached != null) {
            return cached;
        }

        long result = helperTribonacci(n - 3, memo) + helperTribonacci(n - 2, memo) + helperTribonacci(n - 1, memo);
        memo.put(n, result);
        return result;
    }

    public static long tribonacciSimplified(int n) {
        return helperTrib(n, new long[n + 3]);
    }

    private static long helperTrib(int n, long[] memo) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helperTrib(n - 1, memo) + helperTrib(n - 2, memo) + helperTrib(n - 3, memo);
        return memo[n];
    }

    // CHALLENGE 4
    // A robot is in the top left corner of a grid with "rows" rows and "cols" columns, and it has to reach the
    // bottom right corner. It can only move one cell to the right or one cell down. Returns how many different paths
    // it can follow. rows and cols are between 1 and 30.
    // Example: gridPaths(1, 1)  returns 1
    //          gridPaths(2, 2)  returns 2
    //          gridPaths(3, 3)  returns 6
    //          gridPaths(2, 5)  returns 5
    public static long gridPaths(int rows, int cols) {
        return paths(rows, cols, new long[rows + 1][cols + 1]);
    }

    public static long paths(int r, int c, long[][] memo) {
        if (r == 1 || c == 1) return 1;

        if (memo[r][c] != 0) return memo[r][c];

        memo[r][c] = paths(r - 1, c, memo) + paths(r, c - 1, memo);

        return memo[r][c];
    }

    // CHALLENGE 5
    // Returns how many different groups of k elements can be chosen from n elements (the order does not matter),
    // which is the number in row n and position k of Pascal's triangle. Each number of the triangle is the sum of the
    // two numbers above it, and the numbers at the sides are 1. n is between 0 and 60 and k is between 0 and n.
    // Example: binomial(5, 2)  returns 10
    //          binomial(4, 0)  returns 1
    //          binomial(6, 6)  returns 1
    //          binomial(6, 3)  returns 20
    public static long binomial(int n, int k) {
        return choose(n, k, new long[n + 1][k + 1]);
    }

    public static long choose(int n, int k, long[][] memo) {
        if (k == 0 || k == n) return 1;

        if (memo[n][k] != 0) return memo[n][k];

        memo[n][k] = choose(n - 1, k - 1, memo) + choose(n - 1, k, memo);
        return memo[n][k];
    }

    // CHALLENGE 6
    // Returns the n-th Catalan number: 1, 1, 2, 5, 14, 42, ... The first one (n = 0) is 1, and for n greater than 0
    // it is the sum, for i from 0 to n-1, of catalan(i) multiplied by catalan(n-1-i). n is between 0 and 30.
    // Example: catalan(0)  returns 1
    //          catalan(1)  returns 1
    //          catalan(3)  returns 5
    //          catalan(5)  returns 42
    public static long catalan(int n) {
        return helperCatalan(n, new long[n + 1]);
    }

    public static long helperCatalan(int n, long[] memo) {
        if (n == 0) return 1;

        if (memo[n] != 0) return memo[n];

        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += helperCatalan(i, memo) * helperCatalan(n - 1 - i, memo);
        }
        memo[n] = sum;
        return memo[n];
    }

    // CHALLENGE 7
    // The triangle has rows of growing size: the first row has 1 number, the second row has 2 numbers, and so on.
    // You start at the top and, in each step, you go to the row below, to the number just below or to the number just
    // below and to the right (the position stays or grows by 1). Returns the greatest sum you can get from the top
    // to the last row, adding the numbers you visit. The triangle has between 1 and 40 rows and the numbers are
    // between -100 and 100.
    // Example: maxTrianglePath([[2], [3, 4], [6, 5, 7]])  returns 13     (2 + 4 + 7)
    //          maxTrianglePath([[5]])                     returns 5
    //          maxTrianglePath([[-1], [-5, -2]])          returns -3
    public static int maxTrianglePath(int[][] triangle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Each number of the array is the money in a house along a street. You cannot take the money of two houses that
    // are next to each other. Returns the greatest amount of money you can take. All the numbers are zero or greater.
    // An empty array gives 0. The array has at most 60 numbers.
    // Example: maxRobbery([1, 2, 3, 1])     returns 4     (1 + 3)
    //          maxRobbery([2, 7, 9, 3, 1])  returns 12    (2 + 9 + 1)
    //          maxRobbery([5])              returns 5
    //          maxRobbery([])               returns 0
    public static int maxRobbery(int[] houses) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns the smallest number of coins needed to make exactly the given amount. The coins are different positive
    // numbers and you can use each one as many times as you want. If the amount cannot be made, returns -1. An amount
    // of 0 needs 0 coins. The amount is between 0 and 300.
    // Example: minCoins([1, 2, 5], 11)  returns 3     (5 + 5 + 1)
    //          minCoins([2], 3)         returns -1
    //          minCoins([1, 5, 6], 10)  returns 2     (5 + 5)
    //          minCoins([3], 0)         returns 0
    public static int minCoins(int[] coins, int amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns how many texts of the given length can be written using only the characters '0' and '1' without two
    // '1' next to each other. A length of 0 has 1 text (the empty text). The length is between 0 and 80.
    // Example: noConsecutiveOnes(0)  returns 1
    //          noConsecutiveOnes(1)  returns 2     (0, 1)
    //          noConsecutiveOnes(2)  returns 3     (00, 01, 10)
    //          noConsecutiveOnes(4)  returns 8
    public static long noConsecutiveOnes(int length) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // A staircase has n steps and each time you can climb any of the numbers of steps in the array. Returns how many
    // different ways there are to reach the top exactly, where two ways are different if the order of the moves is
    // different. The numbers in the array are different and positive. A staircase with 0 steps has 1 way. n is between
    // 0 and 50 and the array has between 1 and 4 numbers.
    // Example: waysToClimb(4, [1, 2])     returns 5
    //          waysToClimb(3, [1, 2, 3])  returns 4
    //          waysToClimb(5, [2])        returns 0
    //          waysToClimb(0, [3])        returns 1
    public static long waysToClimb(int n, int[] steps) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

package dev.perfectbogus.recursion.memoization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class MemoizationEasyChallenge {

    // Solve every challenge with recursion and memoization. The big tests are too slow for a plain recursion.

    // ==========================================================
    // CHALLENGE 1
    // ==========================================================
    // Returns the n-th Fibonacci number. fibonacci(0) is 0, fibonacci(1) is 1 and every other number is the sum of
    // the two previous ones. n is between 0 and 90.
    //
    // Examples:
    //   0   ->  0
    //   1   ->  1
    //   10  ->  55
    //   20  ->  6765
    public static long fibonacci(int n) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return helperFib(n, memo);
    }

    private static long helperFib(int n, long[] memo) {
        if (n <= 1) return n;

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = helperFib(n - 1, memo) + helperFib(n - 2, memo);
        return memo[n];
    }

    // ==========================================================
    // CHALLENGE 2
    // ==========================================================
    // A staircase has n steps. Each move climbs 1 or 2 steps. Returns in how many different ways you can reach the
    // top (the order of the moves matters). n is between 0 and 90. With 0 steps there is 1 way: not moving.
    //
    // Examples:
    //   0  ->  1
    //   1  ->  1
    //   2  ->  2   (1+1, 2)
    //   5  ->  8
    public static long climbStairs(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return helperClimb(n, memo);
    }

    private static long helperClimb(int n, Map<Integer, Long> memo) {
        if (n == 0 || n == 1) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        long result = helperClimb(n - 1, memo) + helperClimb(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // ==========================================================
    // CHALLENGE 3
    // ==========================================================
    // Returns the n-th Tribonacci number. tribonacci(0) is 0, tribonacci(1) is 1, tribonacci(2) is 1 and every other
    // number is the sum of the three previous ones. n is between 0 and 70.
    //
    // Examples:
    //   0  ->  0
    //   2  ->  1
    //   4  ->  4
    //   7  ->  24
    public static long tribonacci(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return helperTri(n, memo);
    }

    private static long helperTri(int n, Map<Integer, Long> memo) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        long result = helperTri(n - 1, memo) + helperTri(n - 2, memo) + helperTri(n - 3, memo);
        memo.put(n, result);
        return result;
    }

    // ==========================================================
    // CHALLENGE 4
    // ==========================================================
    // Returns how many texts of n characters made only of '0' and '1' do not have two '1' next to each other. n is
    // between 0 and 90. The empty text (n = 0) counts as 1.
    //
    // Examples:
    //   0  ->  1
    //   1  ->  2   ("0", "1")
    //   2  ->  3   ("00", "01", "10")
    //   5  ->  13
    public static long countWithoutConsecutiveOnes(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return helperCount(n, memo);
    }

    private static long helperCount(int n, Map<Integer, Long> memo) {
        if (n == 0) return 1;
        if (n == 1) return 2;

        if (memo.containsKey(n)) return memo.get(n);

        long result = helperCount(n - 1, memo) + helperCount(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // ==========================================================
    // CHALLENGE 5
    // ==========================================================
    // A grid has rows rows and cols columns. Starting in the top-left cell, you can only move one cell to the right
    // or one cell down. Returns how many different paths reach the bottom-right cell. rows and cols are between 1
    // and 30.
    //
    // Examples:
    //   (1, 1)  ->  1
    //   (2, 2)  ->  2
    //   (3, 7)  ->  28
    //   (4, 4)  ->  20
    public static long gridPaths(int rows, int cols) {
        long[][] memo = new long[rows + 1][cols + 1];
        return helperGrid(rows, cols, memo);
    }

    private static long helperGrid(int rows, int cols, long[][] memo) {
        if (rows == 1 || cols == 1) return 1;

        if (memo[rows][cols] != 0) return memo[rows][cols];

        memo[rows][cols] = helperGrid(rows - 1, cols, memo) + helperGrid(rows, cols - 1, memo);
        return memo[rows][cols];
    }

    public static long gridPathsString(int rows, int cols) {
        Map<String, Long> memo = new HashMap<>();
        return helperGridString(rows, cols, memo);
    }

    private static long helperGridString(int rows, int cols, Map<String, Long> memo) {
        if (rows == 1 || cols == 1) return 1;

        final String key = String.valueOf(rows) + cols;
        if (memo.containsKey(key)) return memo.get(key);

        long result = helperGridString(rows - 1, cols, memo) + helperGridString(rows, cols - 1, memo);
        memo.put(key, result);
        return result;
    }

    // ==========================================================
    // CHALLENGE 6
    // ==========================================================
    // Returns the number of ways to choose k items from n items (the binomial coefficient). Use the rule
    // binomial(n, k) = binomial(n - 1, k - 1) + binomial(n - 1, k), with binomial(n, 0) = binomial(n, n) = 1.
    // n is between 0 and 60 and k is between 0 and n.
    //
    // Examples:
    //   (5, 2)  ->  10
    //   (6, 3)  ->  20
    //   (5, 0)  ->  1
    //   (5, 5)  ->  1
    public static long binomial(int n, int k) {
        Map<String, Long> memo = new HashMap<>();
        return helperBinomial(n, k, memo);
    }

    private static long helperBinomial(int n, int k, Map<String, Long> memo) {
        if (k == 0 || n == k) return 1;

        final String key = n + "," + k;
        if (memo.containsKey(key)) return memo.get(key);

        long result = helperBinomial(n - 1, k - 1, memo) + helperBinomial(n - 1, k, memo);
        memo.put(key, result);
        return result;
    }

    public static long binomialArray(int n, int k) {
        long[][] memo = new long[n + 1][k + 1];
        return helperBinomialArray(n, k, memo);
    }

    public static long helperBinomialArray(int n, int k, long[][] memo) {
        if (k == 0 || n == k) return 1;

        if (memo[n][k] != 0) return memo[n][k];

        long result = helperBinomialArray(n - 1, k - 1, memo) + helperBinomialArray(n - 1, k, memo);
        memo[n][k] = result;
        return result;
    }

    // ==========================================================
    // CHALLENGE 7
    // ==========================================================
    // Returns the n-th Catalan number. catalan(0) is 1 and, for n > 0, catalan(n) is the sum of
    // catalan(i) * catalan(n - 1 - i) for every i from 0 to n - 1. n is between 0 and 30.
    //
    // Examples:
    //   0  ->  1
    //   3  ->  5
    //   4  ->  14
    //   5  ->  42
    public static long catalan(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return helperCatalan(n, memo);
    }

    private static long helperCatalan(int n, Map<Integer, Long> memo) {
        if (n == 0) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += helperCatalan(i, memo) * helperCatalan(n - 1 - i, memo);
        }

        memo.put(n, sum);
        return sum;
    }

    public static long catalanArray(int n) {
        long[] memo = new long[n + 1];
        return helperCatalanArray(n, memo);
    }

    private static long helperCatalanArray(int n, long[] memo) {
        if (n == 0) return 1;

        if (memo[n] != 0) return memo[n];

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += helperCatalanArray(i, memo) * helperCatalanArray(n - 1 - i, memo);
        }
        memo[n] = result;
        return result;
    }

    // ==========================================================
    // CHALLENGE 8
    // ==========================================================
    // Returns the smallest amount of perfect squares (1, 4, 9, 16, ...) that add up to n. A square can be used as
    // many times as you want. n is between 0 and 1000, and 0 needs 0 squares.
    //
    // Examples:
    //   0   ->  0
    //   12  ->  3   (4 + 4 + 4)
    //   13  ->  2   (4 + 9)
    //   7   ->  4   (4 + 1 + 1 + 1)
    public static int minSquares(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return helperSquares(n, memo);
    }

    private static int helperSquares(int n, int[] memo) {
        if (n == 0) return 0;

        if (memo[n] != -1) return memo[n];

        int best = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            best = Math.min(best, 1 + helperSquares(n - i * i, memo));
        }
        memo[n] = best;
        return best;
    }

    public static int minSquaresMap(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return helperMinSquareMap(n, memo);
    }

    public static int helperMinSquareMap(int n, Map<Integer, Integer> memo) {
        if (n == 0) return 0;

        if (memo.containsKey(n)) return memo.get(n);

        int best = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            best = Math.min(best, 1 + helperMinSquareMap(n - i * i, memo));
        }
        memo.put(n, best);
        return best;
    }

    // ==========================================================
    // CHALLENGE 9
    // ==========================================================
    // Every value is the money of a house in a street. You cannot take the money of two houses that are next to each
    // other. Returns the most money you can take. The values are not negative and there are up to 1000 houses.
    //
    // Examples:
    //   [2, 7, 9, 3, 1]  ->  12   (2 + 9 + 1)
    //   [1, 2, 3, 1]     ->  4    (1 + 3)
    //   [5]              ->  5
    //   []               ->  0
    public static long houseRobber(int[] values) {
        long[] memo = new long[values.length];
        Arrays.fill(memo, -1);
        return helperHouseRobber(values, 0, memo);
    }

    private static long helperHouseRobber(int[] values, int i, long[] memo) {
        if (i >= values.length) return 0;

        if (memo[i] != -1) return memo[i];

        memo[i] = Math.max(
                values[i] + helperHouseRobber(values, i + 2, memo),
                helperHouseRobber(values, i + 1, memo)
        );

        return memo[i];
    }

    public static long houseRobberMap(int[] values) {
        Map<Integer, Long> memo = new HashMap<>();
        return helperHouseRobberMap(values, 0, memo);
    }

    private static long helperHouseRobberMap(int[] values, int i, Map<Integer, Long> memo) {
        if (i >= values.length) return 0;

        if (memo.containsKey(i)) return memo.get(i);

        long result = Math.max(
                values[i] + helperHouseRobberMap(values, i + 2, memo),
                helperHouseRobberMap(values, i + 1, memo)
        );
        memo.put(i, result);

        return result;
    }

    // ==========================================================
    // CHALLENGE 10
    // ==========================================================
    // Returns the smallest number of coins that add up exactly to amount. Every coin value can be used as many times
    // as you want. If it is not possible, returns -1. The coins are positive and different, and amount is between 0
    // and 1000. With amount 0 you need 0 coins.
    //
    // Examples:
    //   ([1, 2, 5], 11)  ->  3    (5 + 5 + 1)
    //   ([2], 3)         ->  -1
    //   ([1, 3, 4], 6)   ->  2    (3 + 3)
    //   ([5], 0)         ->  0
    public static int minCoins(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return helperMinCoins(coins, amount, memo);
    }

    private static int helperMinCoins(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;

        if (memo[amount] != -2) return memo[amount];

        int best = -1;
        for (int coin : coins) {
            if (coin <= amount) {
                int rest = helperMinCoins(coins, amount - coin, memo);
                if (rest != -1 && (best == -1 || rest + 1 < best)) {
                    best = rest + 1;
                }
            }
        }
        memo[amount] = best;
        return best;
    }

    // ==========================================================
    // CHALLENGE 11
    // ==========================================================
    // Returns in how many different ways you can add up to n using the step sizes of the array. A step size can be
    // used as many times as you want, and the order matters: 1+2 and 2+1 are different ways. The step sizes are
    // positive and different, and n is between 0 and 50. With n = 0 there is 1 way: using no steps.
    //
    // Examples:
    //   (4, [1, 2])  ->  5
    //   (3, [1, 3])  ->  2    (1+1+1, 3)
    //   (5, [2])     ->  0
    //   (0, [3])     ->  1
    public static long countWays(int n, int[] steps) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return ways(n, steps, memo);
    }

    private static long ways(int n, int[] steps, long[] memo) {
        if (n == 0) return 1;

        if (memo[n] != -1) return memo[n];

        long total = 0;
        for (int step : steps) {
            if (step <= n) {
                total += ways(n - step, steps, memo);
            }
        }
        memo[n] = total;
        return total;
    }

    // ==========================================================
    // CHALLENGE 12
    // ==========================================================
    // Returns true if some of the numbers (each one used at most once) add up exactly to target. The numbers are not
    // negative, there are up to 40 of them and target is between 0 and 2000. A target of 0 is always possible by
    // using no numbers.
    //
    // Examples:
    //   ([3, 34, 4, 12, 5, 2], 9)  ->  true    (4 + 5)
    //   ([3, 34], 10)              ->  false
    //   ([], 0)                    ->  true
    //   ([5], 0)                   ->  true
    public static boolean canMakeSum(int[] nums, int target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13
    // ==========================================================
    // Like challenge 5, but some cells are blocked (true in the array) and a path cannot go through them. Starting in
    // the top-left cell, you can only move one cell to the right or one cell down. Returns how many different paths
    // reach the bottom-right cell. If the first or the last cell is blocked, there are 0 paths. The grid has between
    // 1 and 25 rows and columns.
    //
    // Examples:
    //   [[false, false, false], [false, true, false], [false, false, false]]  ->  2
    //   [[false, false], [false, false]]                                      ->  2
    //   [[true]]                                                              ->  0
    //   [[false]]                                                             ->  1
    public static long gridPathsWithBlocks(boolean[][] blocked) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14
    // ==========================================================
    // The triangle has at least one row, and row i has i + 1 values. You start in the top value and, from each
    // value, you move to one of the two values just below it: the one in the same position or the one in the next
    // position. Returns the biggest sum of the values of a path from the top to the last row. There are up to 40 rows.
    //
    // Examples:
    //   [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]  ->  21   (2 + 4 + 7 + 8)
    //   [[5]]                                   ->  5
    //   [[1], [2, 3]]                           ->  4
    //   [[0], [0, 0]]                           ->  0
    public static int maxTrianglePath(int[][] triangle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15
    // ==========================================================
    // Returns the length of the longest common subsequence of two texts: the longest group of characters that appear
    // in both texts in the same order, not necessarily next to each other. Each text has up to 100 characters and the
    // case matters.
    //
    // Examples:
    //   ("abcde", "ace")         ->  3   ("ace")
    //   ("abc", "def")           ->  0
    //   ("", "abc")              ->  0
    //   ("AGGTAB", "GXTXAYB")    ->  4   ("GTAB")
    public static int longestCommonSubsequence(String a, String b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

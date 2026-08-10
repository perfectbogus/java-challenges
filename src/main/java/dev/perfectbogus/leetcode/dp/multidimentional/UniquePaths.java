package dev.perfectbogus.leetcode.dp.multidimentional;

import java.util.Arrays;

public class UniquePaths {

    public static void main(String[] args) {
        int m = 5;
        int n = 5;

        System.out.println(uniquePaths(m, n));
    }

    public static int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(m, n, 0, 0, memo);
    }

    public static int dp(int m, int n, int idx, int jdx, int[][] memo) {
        System.out.println("idx: " + idx + " jdx: " + jdx + " memo:" + Arrays.deepToString(memo));

        if (idx == m - 1 && jdx == n - 1) return 1;

        if (memo[idx][jdx] != -1) return memo[idx][jdx];

        int rightPaths = 0;
        int downPaths = 0;

        if (idx < m - 1) rightPaths = dp(m, n, idx + 1, jdx, memo);

        if (jdx < n - 1) downPaths = dp(m, n, idx, jdx + 1, memo);

        memo[idx][jdx] = rightPaths + downPaths;
        System.out.println("idx: " + idx + " jdx: " + jdx + " memo:" + Arrays.deepToString(memo));
        return memo[idx][jdx];
    }
}

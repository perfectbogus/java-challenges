package dev.perfectbogus.leetcode.dp;

public class MinCostStairs {

    public static void main(String[] args) {
        int[] costs = new int[] {1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostStairs(costs));

        System.out.println(minCostStairs(new int[] {10,15,20} ));
    }

    public static int minCostStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }
}

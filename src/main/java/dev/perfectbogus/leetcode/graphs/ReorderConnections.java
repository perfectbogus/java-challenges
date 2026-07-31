package dev.perfectbogus.leetcode.graphs;

import java.util.Arrays;

public class ReorderConnections {

    public static void main(String[] args) {
        int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        int n = 6;

        System.out.println(minReorder(n, connections));
    }
    public static int minReorder(int n, int[][] connections) {
        int[] head = new int[n];
        Arrays.fill(head, -1);

        int m = connections.length;
        int[] to = new int[2 * m];
        int[] next = new int[2 * m];
        int[] cost = new int[2 * m];
        int idx = 0;

        for (int[] e : connections) {
            int u = e[0];
            int v = e[1];

            to[idx] = v;
            cost[idx] = 1;
            next[idx] = head[u];
            head[u] = idx++;

            to[idx] = u;
            cost[idx] = 0;
            next[idx] = head[v];
            head[v] = idx++;
        }

        boolean[] vis = new boolean[n];
        return dfs(0, head, to, next, cost, vis);
    }

    private static int dfs(int u, int[] head, int[] to, int[] next, int[] cost, boolean[] vis) {
        vis[u] = true;
        int res = 0;

        for (int i = head[u]; i != -1; i = next[i]) {
            int v = to[i];
            if (!vis[v]) {
                res += cost[i];
                res += dfs(v, head, to, next, cost, vis);
            }
        }

        return res;
    }
}

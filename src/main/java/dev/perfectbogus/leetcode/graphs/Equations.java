package dev.perfectbogus.leetcode.graphs;

import java.util.*;

public class Equations {

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(List.of("a", "b"));
        equations.add(List.of("b", "c"));

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(List.of("a", "c"));
        queries.add(List.of("b", "a"));
        queries.add(List.of("a", "e"));
        queries.add(List.of("a", "a"));

        double[] res = calcEquation(equations, values, queries);

        for (double x : res) {
            System.out.println(x);
        }
    }

    static class Pair {
        String des;
        double cost;

        public Pair(String _des, double _cost) {
            this.des = _des;
            this.cost = _cost;
        }
    }

    public static double dfs(String src, String des, Set<String> vis, Map<String, List<Pair>> adj) {
        if (!adj.containsKey(src) || !adj.containsKey(des)) return -1.0;
        if (src.equals(des)) return 1.0;
        vis.add(src);
        for(Pair p : adj.get(src)) {
            if (!vis.contains(p.des)) {
                double v = dfs(p.des, des, vis, adj);
                if (v != -1) {
                    return v * p.cost;
                }
            }
        }
        return -1.0;
    }



    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = values.length;
        Map<String, List<Pair>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            if (!adj.containsKey(from)) adj.put(from, new ArrayList<>());
            if (!adj.containsKey(to)) adj.put(to, new ArrayList<>());
            adj.get(from).add(new Pair(to, values[i]));
            adj.get(from).add(new Pair(from, 1/values[i]));
        }

        int len = queries.size();
        double[] ans = new double[len];

        for (int i = 0; i < len; i++) {
            String src = queries.get(i).get(0);
            String des = queries.get(i).get(1);

            Set<String> vis = new HashSet<>();
            double x = dfs(src, des, vis, adj);

            if (x != -1.0) {
                adj.get(src).add(new Pair(des, x));
                adj.get(des).add(new Pair(src, 1/x));
            }
            ans[i] = x;
        }

        return ans;
    }
}

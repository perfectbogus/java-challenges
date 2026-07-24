package dev.perfectbogus.leetcode.hashset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EqualRowColumn {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(equalPairs(grid));
    }

    public static int equalPairs(int[][] grid) {

        int n = grid.length;
        int pairs = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String row = Arrays.toString(grid[i]);
            map.merge(row, 1, Integer::sum);
        }

        for (int j = 0; j < n; j++) {
            int[] col = new int[n];
            for (int i = 0; i < n; i++) {
                col[i] = grid[i][j];
            }
            String colVal = Arrays.toString(col);
            if (map.containsKey(colVal)) {
                pairs += map.get(colVal);
            }
        }

        return pairs;
    }
}

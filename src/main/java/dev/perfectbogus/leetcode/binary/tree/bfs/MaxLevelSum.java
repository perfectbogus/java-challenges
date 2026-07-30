package dev.perfectbogus.leetcode.binary.tree.bfs;

import dev.perfectbogus.leetcode.binary.tree.dfs.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MaxLevelSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(7, new TreeNode(7), new TreeNode(-8)), new TreeNode(0));

        System.out.println(maxLevelSum(root));
    }

    public static int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> sums = new HashMap<>();
        int level = 1;
        int max = root.val;

        bfs(root, sums, level);

        for (Map.Entry<Integer, Integer> entry : sums.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                level = entry.getKey();
            }
        }

        return level;
    }

    public static void bfs(TreeNode node, Map<Integer, Integer> sums, int level) {
        if (node == null) return;

        sums.merge(level, node.val, Integer::sum);

        int nextLevel = level + 1;
        bfs(node.left, sums, nextLevel);
        bfs(node.right, sums, nextLevel);
    }
}

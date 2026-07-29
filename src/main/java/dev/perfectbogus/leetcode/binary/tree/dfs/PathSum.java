package dev.perfectbogus.leetcode.binary.tree.dfs;

import java.util.HashMap;
import java.util.Map;

public class PathSum {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));
//        System.out.println(pathSum(root, 8));

        TreeNode root2 = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))), new TreeNode(-3, null, new TreeNode(11)));
        System.out.println(pathSum(root2, 8));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, 0L, targetSum, prefix);
    }

    private static int dfs(TreeNode node, long currSum, int target, Map<Long, Integer> prefix) {
        if (node == null) return 0;

        currSum += node.val;
        int count = prefix.getOrDefault(currSum - target, 0);
        prefix.merge(currSum, 1, Integer::sum);
        count += dfs(node.left, currSum, target, prefix);
        count += dfs(node.right, currSum, target, prefix);
        prefix.merge(currSum, -1, Integer::sum);
        return count;
    }
}

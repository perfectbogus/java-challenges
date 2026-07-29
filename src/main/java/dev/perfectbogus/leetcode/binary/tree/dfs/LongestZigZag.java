package dev.perfectbogus.leetcode.binary.tree.dfs;

public class LongestZigZag {

    static enum FROM {
        LEFT, RIGHT
    }

    static int res = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(1, new TreeNode(1), new TreeNode(1)), new TreeNode(1));
        System.out.println(longestZigZag(root));
    }

    public static int longestZigZag(TreeNode root) {
        helper(root.left, 1, FROM.LEFT);
        helper(root.right, 1, FROM.RIGHT);
        return res;
    }

    public static void helper(TreeNode node, int len, FROM from) {
        if (node == null) return;

        res = Math.max(res, len);

        if (from.equals(FROM.LEFT)) {
            helper(node.right, len + 1, FROM.RIGHT);
            helper(node.left, 1, FROM.LEFT);
        } else {
            helper(node.right, 1, FROM.RIGHT);
            helper(node.left, len + 1, FROM.LEFT);
        }
    }
}

package dev.perfectbogus.leetcode.binary.tree.dfs;

public class MaxDepthBinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}

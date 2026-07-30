package dev.perfectbogus.leetcode.binary.tree.dfs;

public class LongestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))), new TreeNode(1, new TreeNode(0) ,new TreeNode(8)));
        TreeNode q = new TreeNode(5);
        TreeNode p = new TreeNode(1);
        System.out.println(lowestCommonAncestor(root, p, q));
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

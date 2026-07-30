package dev.perfectbogus.leetcode.binary.search.tree;

import dev.perfectbogus.leetcode.binary.tree.dfs.TreeNode;

public class SearchIn {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7));

        TreeNode res = searchBST(root, 2);
        System.out.println(res.val);
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        if (val == root.val) return root;
        else if (val > root.val) return searchBST(root.right, val);
        else return searchBST(root.left, val);
    }
}

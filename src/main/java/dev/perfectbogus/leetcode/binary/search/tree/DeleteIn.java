package dev.perfectbogus.leetcode.binary.search.tree;

import dev.perfectbogus.leetcode.binary.tree.dfs.TreeNode;

public class DeleteIn {

    public static void main(String[] args) {
        // Cases:
        // Node has no children
        //TreeNode root = new TreeNode(5, new TreeNode(4), new TreeNode(6));
        // Node has
        TreeNode root = new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)), new TreeNode(6, null, new TreeNode(7)));


        TreeNode res = deleteNode(root, 4);
        printTree(res);

    }


    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        } else {
            System.out.println(root.val);
            printTree(root.left);
            printTree(root.right);
        }
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode rightSmallest = root.right;
            while (rightSmallest.left != null) {
                rightSmallest = rightSmallest.left;
            }
            rightSmallest.left = root.left;
            return root.right;
        }
        return root;
    }
}

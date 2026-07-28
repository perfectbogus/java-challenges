package dev.perfectbogus.leetcode.binary.tree.dfs;

import java.util.ArrayList;

public class LeafSimilarTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.println(leafSimilar(root1, root2));
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        helper(list1, root1);
        helper(list2, root2);

        return list1.equals(list2);
    }

    public static void helper(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            list.add(root.val);
        } else {
            helper(list, root.left);
            helper(list, root.right);
        }
    }
}

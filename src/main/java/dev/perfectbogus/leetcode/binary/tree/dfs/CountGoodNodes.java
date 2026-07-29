package dev.perfectbogus.leetcode.binary.tree.dfs;

public class CountGoodNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.println(helper(Integer.MIN_VALUE, root));
    }

    public static int helper(int max, TreeNode root) {
        if (root == null) return 0;

        int count = 0;
        if (root.val > max) {
            max = root.val;
            count = 1;
        }

        count += helper(max, root.left);
        count += helper(max, root.right);

        return count;
    }


}

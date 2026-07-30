package dev.perfectbogus.leetcode.binary.tree.bfs;

import dev.perfectbogus.leetcode.binary.tree.dfs.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {

    /**
     *              5     <----
     *          1      7  <----
     *       6    8       <----
     *
     *       result 5 7 8
     * @param args
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(1, new TreeNode(6), new TreeNode(8)), new TreeNode(7));
        List<Integer> res = rightSideView(root);

        for (int x : res) {
            System.out.println(x);
        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        bfs(root, res, 0);
        return res;
    }

    public static void bfs(TreeNode node, List<Integer> res, int currDepth) {
        if (node == null) return;

        if (res.size() == currDepth) res.add(node.val);

        int nextDepth = currDepth + 1;
        bfs(node.right, res, nextDepth);
        bfs(node.left, res, nextDepth);
    }
}

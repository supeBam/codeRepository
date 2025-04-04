package org.example.leecode.Lc20250331;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/description/?envType=daily-question&envId=2025-04-04
public class Lc1123 {
    // 维护最大深度
    int maxDeep = -1;
    private TreeNode ans;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public int dfs(TreeNode root, int deep){
        // 如果没有子节点了，更新最深层
        if (root == null){
            maxDeep = Math.max(maxDeep, deep);
            return deep;
        }

        int leftDeep = dfs(root.left, deep + 1);
        int rightDeep = dfs(root.right, deep + 1);
        // 当前节点的左右深度一样，且当前节点和最深节点深度一样
        if (leftDeep == rightDeep && leftDeep == maxDeep) {
            ans = root;
        }
        // 更新左右深度
        return Math.max(leftDeep, rightDeep);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

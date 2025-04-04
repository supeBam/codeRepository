package org.example.leecode.Lc20250331;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/solutions/2428724/liang-chong-di-gui-si-lu-pythonjavacgojs-xxnk/?envType=daily-question&envId=2025-04-04
public class Lc236 {
    public static void main(String[] args) {
//        [3,5,1,6,2,0,8,null,null,7,4] 请根据这个数组来创建TreeNode类
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        Lc236 lc236 = new Lc236();
        System.out.println(lc236.lowestCommonAncestor(root, root.left,  root.right.right).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 找到 p 或 q 则不需要向下遍历吗， 如果存在，则当前节点就是公共祖先节点， 不存在则不需要向下遍历
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 有左右节点，则当前节点就是 最近的公共祖先
        if (left != null && right != null){
            return root;
        }

        return left != null? left : right;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

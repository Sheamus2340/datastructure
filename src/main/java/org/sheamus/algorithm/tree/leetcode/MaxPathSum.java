package org.sheamus.algorithm.tree.leetcode;

import org.sheamus.algorithm.tree.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 *
 * @link https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class MaxPathSum {

    /**
     * 思路：
     * 拆解问题，实际问题分为两种情况：
     * 1. 经过 root 节点，那么最大的路径之和是 Max(left) + Max(right) + root.val
     * 2. 不经过 root 节点，那么就是 Max(Max(left),  Max(right))
     *
     * @param root
     * @return
     */
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    /**
     * 返回经过root的单边分支最大和， 即Math.max(root, root+left, root+right)
     *
     * @param root
     * @return
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左边分支最大值，左边分支如果为负数还不如不选择
        int leftMax = dfs(root.left);
        // 计算右边分支最大值，右边分支如果为负数还不如不选择
        int rightMax = dfs(root.right);
        // left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, root.val + leftMax + rightMax);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        return Math.max(0, root.val + Math.max(leftMax, rightMax));
    }


    public static void main(String[] args) {
        // root = [1,2,3]
        TreeNode left1 = new TreeNode(1, null, null);
        TreeNode right1 = new TreeNode(1, null, null);
        TreeNode left2 = new TreeNode(2, null, null);
        TreeNode right2 = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(1, left1, right1);
        TreeNode right = new TreeNode(3, left2, right2);
        TreeNode root = new TreeNode(2, left, right);

        MaxPathSum maxPathSum = new MaxPathSum();
        int sum = maxPathSum.maxPathSum(root);
        System.out.println(sum);
    }


}

package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class DiameterOfBinaryTree {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    /**
     * 函数传入当前节点 u，返回以该节点为根时，方向“往下”的最大路径节点数量,不包含当前节点（注意这里是点数，而不是题目要求的边数）。
     *
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }


}

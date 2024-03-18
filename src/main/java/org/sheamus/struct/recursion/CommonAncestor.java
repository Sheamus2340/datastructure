package org.sheamus.struct.recursion;

/**
 * title：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class CommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode == null) {
            return rightNode;
        } else if (rightNode == null) {
            return leftNode;
        } else {
            return root;
        }
    }

}

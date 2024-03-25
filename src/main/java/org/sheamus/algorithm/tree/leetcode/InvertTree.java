package org.sheamus.algorithm.tree.leetcode;

import org.sheamus.algorithm.tree.TreeNode;

/**
 * title：
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftTreeNode = invertTree(root.left);
        TreeNode rightTreeNode = invertTree(root.right);
        root.left = rightTreeNode;
        root.right = leftTreeNode;
        return root;
    }

}

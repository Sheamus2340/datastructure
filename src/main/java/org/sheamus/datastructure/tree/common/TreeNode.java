package org.sheamus.datastructure.tree.common;

public class TreeNode {

    int data = Integer.MIN_VALUE;
    TreeNode left;
    TreeNode right;

    public TreeNode() {}

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}

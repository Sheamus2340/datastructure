package org.sheamus.datastructure.tree.common;

public class TreeNode {

    int data = Integer.MIN_VALUE;
    TreeNode leftNode;
    TreeNode rightNode;

    public TreeNode() {}

    public TreeNode(int data, TreeNode leftNode, TreeNode rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

}

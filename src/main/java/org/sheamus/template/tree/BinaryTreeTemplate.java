package org.sheamus.template.tree;

/**
 * 二叉树的遍历模版
 */
public class BinaryTreeTemplate {

    public void traverse(TreeNode root) {
        // TODO 前序遍历
        //  遍历 root.val
        traverse(root.left);
        // TODO 中序遍历
        //  遍历 root.val
        traverse(root.right);
        // TODO 后序遍历
        //  遍历 root.val
    }

}

package org.sheamus.template.multitree;

/**
 * 多叉树的遍历
 */
public class MultiTreeTemplate {

    public void traverse(TreeNode root) {
        for (TreeNode child : root.children) {
            traverse(child);
        }
    }

}

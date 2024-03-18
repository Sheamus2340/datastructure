package org.sheamus.struct.recursion;

/**
 * title：
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        traverse(root.left);
        traverse(root.right);
    }


    /**
     * 分解子问题
     */
    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = convert(root.left);
        TreeNode right = convert(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

}

package org.sheamus.struct.recursion;

/**
 * title：分别使用遍历模式和分解子问题模式解题
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        return iterator(root);
    }

    int maxDepth = 0;
    int depth;

    /**
     * 迭代的思想
     *
     * @param root
     * @return
     */
    public int iterator(TreeNode root) {
        traverse(root);
        return maxDepth;
    }

    /**
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (root.left == null && root.right == null) {
            maxDepth = depth;
        }
        traverse(root.left);
        traverse(root.right);
        depth--;
    }

    /**
     * 分拆子问题
     * 求最大深度问题就是 = root + Math.max(左子树的最大深度, 右子树的最大深度)
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }


    public static void main(String[] args) {

        TreeNode root4 = new TreeNode(1, null, null);

        TreeNode root5 = new TreeNode(1, root4, null);

        TreeNode root3 = new TreeNode(1, root5, null);

        TreeNode root1 = new TreeNode(1, root3, null);

        TreeNode root = new TreeNode(1, root1, null);


        MaxDepth maxDepth1 = new MaxDepth();
        int i = maxDepth1.maxDepth(root);
        System.out.println(i);

        System.out.println(maxDepth1.maxDepth2(root));
    }


}

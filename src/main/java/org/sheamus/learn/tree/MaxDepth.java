package org.sheamus.learn.tree;

/**
 * title：
 */
public class MaxDepth {

    int depth = 0;
    int maxDepth = 0;

    /**
     * 遍历思维
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return maxDepth;
        }
        travel(root);
        return maxDepth;
    }

    private void travel(TreeNode root) {
        if (root == null) {
            return;
        }

        depth++;
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        travel(root.left);
        travel(root.right);
        depth--;
    }

    /**
     * 拆解问题的思维：
     * 最深深度就是左右子树中较大的深度
     *
     * @param root
     * @return
     */
    public int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = getMaxDepth(root.left);
        int rightMax = getMaxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }

    public static void main(String[] args) {
        MaxDepth maxDepth1 = new MaxDepth();

        TreeNode left3 = new TreeNode(1);
        TreeNode left2 = new TreeNode(1, left3, null);
        TreeNode leftroot2 = new TreeNode(1, left2, null);
        TreeNode right = new TreeNode(1);
        TreeNode leftroot1 = new TreeNode(1, leftroot2, null);
        TreeNode root = new TreeNode(1, leftroot1, right);

        int depth1 = maxDepth1.maxDepth(root);
        System.out.println(depth1);
        System.out.println(maxDepth1.getMaxDepth(root));

    }


}

package org.sheamus.struct.recursion;

/**
 * title：分别使用遍历模式和分拆子问题模式解决最大直径问题
 */
public class DiameterOfBinaryTree {

    int maxDiameter = 0;


    /**
     * 所谓⼆叉树的「直径」⻓度，就是任意两个结点之间的路径⻓度。
     * 最⻓「直径」并不⼀定要穿过根结点，⽐如下⾯这棵⼆叉树：
     *
     * 遍历思维：
     * 1. 求每一个节点的左右深度之和；
     * 2. 求出结果中最大的；
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxDepth(root);

        return maxDiameter;
    }

    /**
     * 求最大深度
     * @param root
     * @return
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);


        maxDiameter = Math.max(maxDiameter, left + right);

        return Math.max(left ,right) + 1;
    }

    public static void main(String[] args) {

        TreeNode root51 = new TreeNode(1, null, null);

        TreeNode root52 = new TreeNode(1, null, root51);

        TreeNode root53 = new TreeNode(1, null, root52);

        TreeNode root4 = new TreeNode(1, null, null);

        TreeNode root5 = new TreeNode(1, root4, null);

        TreeNode root3 = new TreeNode(1, root5, null);

        TreeNode root1 = new TreeNode(1, root3, root53);

        TreeNode root = new TreeNode(1, root1, null);

        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(root));
    }

}

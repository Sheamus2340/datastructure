package org.sheamus.struct.recursion;

/**
 * title：分别使用遍历模式和分拆子问题模式解决最大直径问题
 */
public class DiameterOfBinaryTree {

    MaxDepth maxDepth = new MaxDepth();

    /**
     * 所谓⼆叉树的「直径」⻓度，就是任意两个结点之间的路径⻓度。
     * 最⻓「直径」并不⼀定要穿过根结点，⽐如下⾯这棵⼆叉树：
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 分拆这个问题
        // 最大直径 = 比较每个节点的左子树深度 + 右子树深度
        if (root == null) {
            return 0;
        }

        int leftMax = diameterOfBinaryTree(root.left);
        int rightMax = diameterOfBinaryTree(root.right);

        if (root.left == null ) {

        }


        int leftDepth = maxDepth.maxDepth2(root.left);
        int rightDepth = maxDepth.maxDepth2(root.right);

        leftDepth = leftDepth == 0 ? 0 : leftDepth - 1;
        rightDepth = rightDepth == 0 ? 0 : rightDepth - 1;

        return 1;
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

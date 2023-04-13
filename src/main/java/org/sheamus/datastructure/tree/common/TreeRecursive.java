package org.sheamus.datastructure.tree.common;

import java.util.LinkedList;

/**
 * 二叉树的递归实现
 * 递归的规律：
 * 1. 终止条件；
 * 2. 本层需要做什么；
 * 3. 本层能为下层传什么值；
 */
public class TreeRecursive {

    /**
     * 二叉树是否对称
     * 思想：
     * 1. left 和 right 不等，或者 left 和 right 都为空
     * 2. 递归的比较 left.left 和 right.right，递归比较 left.right 和 right.left
     *
     * @param root
     * @return
     */
    public boolean isSymmetry(TreeNode root) {
        if (root == null) {
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }

    /**
     * 深度遍历
     *
     * @param left
     * @param right
     * @return
     */
    private boolean dfs(TreeNode left, TreeNode right) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.data != right.data) {
            return false;
        }
        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    public boolean isSymmetricNotRecursive(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.data != right.data) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(2, null, null);
        //TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);

        TreeNode node3 = new TreeNode(7, node0, null);
        //TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(6, null, null);
        TreeNode node6 = new TreeNode(7, null, node2);
        TreeNode node7 = new TreeNode(6, null, null);

        TreeNode node8 = new TreeNode(8, node5, node3);
        TreeNode node9 = new TreeNode(8, node6, node7);

        TreeNode root = new TreeNode(0, node8, node9);

        TreeRecursive treeRecursive = new TreeRecursive();
        System.out.println(treeRecursive.isSymmetry(root));
        System.out.println(treeRecursive.isSymmetricNotRecursive(root));
    }

}

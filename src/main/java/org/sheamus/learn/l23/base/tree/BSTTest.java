package org.sheamus.learn.l23.base.tree;

import java.util.LinkedList;

public class BSTTest {

    public void level(TreeNode root) {
        int level = 1;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = list.poll();
                System.out.println(cur.data + " 节点在 " + level);
                if (cur.left != null) {
                    list.add(cur.left);
                }
                if (cur.right != null) {
                    list.add(cur.right);
                }
            }
            level++;
        }
    }

    public void levelTravel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        System.out.println(root.data + " 节点在 " + level);
        levelTravel(root.left, level + 1);
        levelTravel(root.right, level + 1);
    }

    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = count(root.left);
        int right = count(root.right);
        System.out.println("当前节点: " + root.data + " , 左子树个数：" + left + " ,右子树个数：" + right);
        return left + right + 1;

    }

    int maxDiameter = 0;

    /**
     * 当前节点的最大的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        // 在后序的位置进行直径的计算
        maxDiameter = Math.max((maxLeft + maxRight + 1), maxDiameter);

        return Math.max(maxLeft, maxRight) + 1;
    }


    public static void main(String[] args) {
        BSTTest bstTest = new BSTTest();
        TreeNode treeNode = BinaryTree.getTreeNode();
        bstTest.level(treeNode);
        bstTest.levelTravel(treeNode, 1);
        bstTest.count(treeNode);
    }

}


package org.sheamus.datastructure.tree.common;

import java.util.Arrays;

/**
 * 构建二叉树
 */
public class CreateBinaryTreeQZ {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 在一个集合中找另一个字符的下标
     *
     * @param array
     * @param v
     * @return
     */
    private static int find(char[] array, char v) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == v) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 通过前、中序遍历构建二叉树
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    private static TreeNode buildTree(char[] preOrder, char[] inOrder) {

        if (preOrder.length == 0) {
            return null;
        }

        //1、根据前序，找到根的值，并且创建根节点
        char rootValue = preOrder[0];
        TreeNode root = new TreeNode(rootValue);

        //2、在中序中找到根的值所在的下标
        int leftIndex = find(inOrder, rootValue);

        //3、切出左子树的前序和中序
        char[] leftPreOrder = Arrays.copyOfRange(preOrder, 1, leftIndex + 1);
        char[] leftInOrder = Arrays.copyOfRange(inOrder, 0, leftIndex);
        root.left = buildTree(leftPreOrder, leftInOrder);

        //4、切出右子树的前序和中序
        char[] rightPreOrder = Arrays.copyOfRange(preOrder, leftIndex + 1, preOrder.length);
        char[] rightInOrder = Arrays.copyOfRange(inOrder, leftIndex + 1, preOrder.length);
        root.right = buildTree(rightPreOrder, rightInOrder);

        return root;
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'A', 'B', 'C', 'D', 'E'};
        char[] inOrder = new char[]{'C', 'D', 'B', 'A', 'E'};
        TreeNode root = buildTree(preOrder, inOrder);
        System.out.println("创建成功");
    }
}


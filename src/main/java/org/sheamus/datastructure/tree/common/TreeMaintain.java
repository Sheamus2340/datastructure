package org.sheamus.datastructure.tree.common;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 树的遍历：
 * 1. 前序、中序、后序
 * 1. 递归
 * 2. 非递归
 * 2. 深度优先；
 * 中序遍历
 * 3. 广度优先；
 */
public class TreeMaintain {
    // 遍历

    // 前序遍历
    // 递归方式
    public void preOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + "\t");
        preOrderRecursive(root.leftNode);
        preOrderRecursive(root.rightNode);
    }

    // 非递归方式

    /**
     * 借助栈
     * 实现思路
     * 1. 根节点压栈：
     * 2. 弹出根节点，打印：
     * a. 判断是否有右节点，有：压入栈；
     * b. 判断是否有左节点，有：压入栈；
     * 3. 弹出栈顶元素，打印：
     * a. 循环2的步骤操作：
     *
     * @param root
     */
    public void preOrderNotRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.data + "\t");
            if (pop.rightNode != null) {
                stack.push(pop.rightNode);
            }
            if (pop.leftNode != null) {
                stack.push(pop.leftNode);
            }
        }
    }

    // 中序遍历
    // 递归方式
    public void inOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(root.leftNode);
        System.out.print(root.data + "\t");
        inOrderRecursive(root.rightNode);
    }

    //非递归方式

    /**
     * 实现思路
     * 使用一个栈和一个辅助引用：
     * 核心思想是看成左节点的遍历
     *
     * @param root
     */
    public void inOrderNotRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (!stack.isEmpty() || curNode != null) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.leftNode;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.print(node.data + "\t");
                curNode = node.rightNode;
            }
        }
    }

    // 后序遍历
    // 递归方式
    public void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.leftNode);
        postOrderRecursive(root.rightNode);
        System.out.print(root.data + "\t");
    }

    // 非递归模式

    /**
     * 双栈实现
     * 左、右、根 -> 根、右、左
     * 和先序遍历有点类似
     *
     * @param root
     */
    public void postOrderNotRecursiveDoubleStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stackReverse = new Stack<>();

        stack.push(root);
        TreeNode curNode;

        while (!stack.isEmpty()) {
            curNode = stack.pop();
            stackReverse.push(curNode);

            if (curNode.leftNode != null) {
                stack.push(curNode.leftNode);
            }

            if (curNode.rightNode != null) {
                stack.push(curNode.rightNode);
            }
        }

        while (!stackReverse.isEmpty()) {
            curNode = stackReverse.pop();
            System.out.print(curNode.data + "\t");
        }

    }

    /**
     * 双指针 + 栈
     *
     * @param root
     */
    public void postOrderNotRecursiveDoublePoint(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 当前处理节点
        TreeNode curNode = root;
        // 处理过的节点
        TreeNode preNode = null;
        while (curNode != null || !stack.isEmpty()) {
            //每次先找到最左边的节点
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.leftNode;
            }
            //弹出栈顶
            TreeNode node = stack.pop();
            //如果该元素的右边没有或是已经访问过
            if (node.rightNode == null || node.rightNode == preNode) {
                //访问中间的节点
                System.out.print(node.data + "\t");
                //且记录为访问过了
                preNode = node;
            } else {
                //该节点入栈
                stack.push(node);
                //先访问右边
                curNode = node.rightNode;
            }
        }

    }

    /**
     * 广度优先遍历
     *
     * @param root
     */
    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (deque.size() != 0) {
            TreeNode poll = deque.poll();
            System.out.print(poll.data + "\t");
            if (poll.leftNode != null) {
                deque.offer(poll.leftNode);
            }

            if (poll.rightNode != null) {
                deque.offer(poll.rightNode);
            }
        }
    }

    // 广度遍历
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);

        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node6 = new TreeNode(6, node1, node4);
        TreeNode node7 = new TreeNode(7, null, node2);

        TreeNode node8 = new TreeNode(8, node5, node3);
        TreeNode node9 = new TreeNode(9, node6, node7);

        TreeNode root = new TreeNode(0, node8, node9);


        TreeMaintain treeMaintain = new TreeMaintain();
        System.out.println("前序遍历--递归模式");
        treeMaintain.preOrderRecursive(root);
        System.out.println("\n前序遍历--非递归模式");
        treeMaintain.preOrderNotRecursive(root);
        System.out.println("\n中序遍历--递归模式");
        treeMaintain.inOrderRecursive(root);
        System.out.println("\n中序遍历--非递归模式");
        treeMaintain.inOrderNotRecursive(root);
        System.out.println("\n后序遍历--递归模式");
        treeMaintain.postOrderRecursive(root);
        System.out.println("\n后序遍历--非递归模式-双栈实现");
        treeMaintain.postOrderNotRecursiveDoubleStack(root);
        System.out.println("\n后序遍历--非递归模式-双指针实现");
        treeMaintain.postOrderNotRecursiveDoublePoint(root);
        System.out.println("\n广度优先遍历");
        treeMaintain.bfs(root);
    }


}

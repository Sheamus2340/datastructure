package org.sheamus.learn.l23.base.tree;

import java.util.Stack;

/**
 * 二叉树的遍历
 */
public class BinaryTree {

    /**
     * 0
     * 3        4
     * 1       2
     * <p>
     * result:
     * 0,3,1,2,4
     *
     * @return
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + "\t");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 前序遍历非递归模式
     * 使用队列的数据结构存储下一个节点
     *
     * @param root
     */
    public void preOrderNoRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.data + "\t");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 0
     * 3        4
     * 1       2
     * <p>
     * result:
     * 1,3,2,0,4
     *
     * @return
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + "\t");
        inOrder(root.right);
    }

    public void inOrderNoRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.data + "\t");
                cur = cur.right;
            }
        }

    }

    /**
     * 0
     * 3        4
     * 1       2
     * <p>
     * result:
     * 1,2,3,4,0
     *
     * @return
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + "\t");
    }

    public void postOrderNoRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> result = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.push(cur);

            if (cur.left != null) {
                stack.push(cur.left);
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        while (!result.isEmpty()) {
            System.out.print(result.pop().data + "\t");
        }

    }

    // 记录最⼤深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;

    void initData(int value) {
        res = value;
        depth = 0;
    }

    // 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
    int maxDepth2(TreeNode root) {
        initData(Integer.MIN_VALUE);
        traverse(root, 1);
        return res;
    }

    int maxDepth3(TreeNode root) {
        initData(Integer.MAX_VALUE);
        traverse(root, 2);
        return res;
    }

    // ⼆叉树遍历框架
    void traverse(TreeNode root, int mode) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶⼦节点，更新最⼤深度
            switch (mode) {
                case 1:
                    res = Math.max(res, depth);
                    break;
                case 2:
                    res = Math.min(res, depth);
                    break;
            }

        }
        traverse(root.left, mode);
        traverse(root.right, mode);
        // 后序位置
        depth--;
    }

    // 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利⽤定义，计算左右⼦树的最⼤深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，
        // 然后再加上根节点⾃⼰
        int res = Math.max(leftMax, rightMax) + 1;
        return res;
    }

    // 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
    int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利⽤定义，计算左右⼦树的最⼤深度
        int leftMin = minDepth(root.left);
        int rightMin = minDepth(root.right);
        // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，
        // 然后再加上根节点⾃⼰
        int res = Math.min(leftMin, rightMin) + 1;
        return res;
    }

    // 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
    int maxDepthSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利⽤定义，计算左右⼦树的最⼤深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，
        // 然后再加上根节点⾃⼰
        int res = leftMax + rightMax + 1;
        return res;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = getTreeNode();
        System.out.println("preOrder:");
        binaryTree.preOrder(root);
        System.out.println("\npreOrderNoRecursion:");
        binaryTree.preOrderNoRecursion(root);
        System.out.println("\r\ninOrder:");
        binaryTree.inOrder(root);
        System.out.println("\r\ninOrderNoRecursion:");
        binaryTree.inOrderNoRecursion(root);
        System.out.println("\r\npostOrder:");
        binaryTree.postOrder(root);
        System.out.println("\r\npostOrderNoRecursion:");
        binaryTree.postOrderNoRecursion(root);
        System.out.println("\r\nmaxDepth:");
        System.out.println(binaryTree.maxDepth(root));
        System.out.println("\r\nmaxDepth2:");
        System.out.println(binaryTree.maxDepth2(root));
        System.out.println("\r\nmaxDepthSum:");
        System.out.println(binaryTree.maxDepthSum(root));
        System.out.println("\r\nminDepth:");
        System.out.println(binaryTree.minDepth(root));
        System.out.println("\r\nminDepth:");
        System.out.println(binaryTree.maxDepth3(root));

    }

    public static TreeNode getTreeNode() {
        TreeNode left01 = new TreeNode(1, null, null);
        TreeNode right01 = new TreeNode(2, null, null);
        TreeNode left02 = new TreeNode(3, left01, right01);
        TreeNode right02 = new TreeNode(4, null, null);
        TreeNode root = new TreeNode(0, left02, right02);
        return root;
    }

}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

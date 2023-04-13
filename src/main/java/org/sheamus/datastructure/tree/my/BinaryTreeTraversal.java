package org.sheamus.datastructure.tree.my;


import java.util.*;

/**
 * 二叉树的遍历
 * 1. 递归
 * 2. 非递归
 * 2.1 栈
 * 2.2 队列
 */
public class BinaryTreeTraversal {

    /**
     * 前序遍历递归模式
     *
     * @param root
     * @return
     */
    public void preOrderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        preOrderTraversalRecursive(root.left, result);
        preOrderTraversalRecursive(root.right, result);
    }

    /**
     * 前序遍历非递归模式
     *
     * @param root
     * @param result
     */
    public void preOrderTraversalNonRecursive(TreeNode root, List<Integer> result) {
        // 清空结果集
        if (!result.isEmpty()) {
            result.clear();
        }

        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            result.add(pop.val);

            if (pop.right != null) {
                stack.push(pop.right);
            }

            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    /**
     * 中序遍历递归模式
     *
     * @param root
     * @param result
     */
    public void inOrderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inOrderTraversalRecursive(root.left, result);
        result.add(root.val);
        inOrderTraversalRecursive(root.right, result);
    }

    /**
     * 中序遍历非递归模式
     *
     * @param root
     * @param result
     */
    public void inOrderTraversalNonRecursive(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
    }

    /**
     * 中序遍历非递归模式
     *
     * @param root
     * @param result
     */
    public void inOrderTraversalNonRecursive2(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                cur = pop.right;
            }
        }
    }

    /**
     * 后续遍历
     *
     * @param root
     * @param result
     */
    public void posOrderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        posOrderTraversalRecursive(root.left, result);
        posOrderTraversalRecursive(root.right, result);
        result.add(root.val);
    }

    /**
     * 后序遍历 - 非递归模式
     * 两个栈实现
     *
     * @param root
     * @param result
     */
    public void posOrderTraversalNonRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // 输出的是根、右、左
        Stack<TreeNode> reStack = new Stack<>();

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            reStack.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }

            if (pop.right != null) {
                stack.push(pop.right);
            }
        }

        while (!reStack.isEmpty()) {
            result.add(reStack.pop().val);
        }
    }

    /**
     * 后序遍历 - 非递归模式
     * 两个引用标识
     *
     * @param root
     * @param result
     */
    public void posOrderTraversalNonRecursive2(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return;
        }
        // 上次处理的节点
        TreeNode pre = null;
        // 当前节点
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            // 注意这里是peek
            cur = stack.peek();

            if (cur.left != null && pre != cur.left && pre != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && pre != cur.right) {
                stack.push(cur.right);
            } else {
                cur = stack.pop();
                result.add(cur.val);
                pre = cur;
            }
        }
    }

    /**
     * 广度优先遍历
     * 队列
     *
     * @param root
     * @param result
     */
    public void bfs(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();

        deque.offer(root);

        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            result.add(poll.val);
            if (poll.left != null) {
                deque.offer(poll.left);
            }
            if (poll.right != null) {
                deque.offer(poll.right);
            }
        }
    }

    /**
     * 广度优先遍历
     * 队列
     *
     * @param root
     * @param result
     */
    public void bfs2(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();

        deque.offer(root);

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.pop();
                result.add(poll.val);
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }

        }

    }

    /**
     * 广度优先遍历
     * 队列 + 层级 + 每一层的元素
     *
     * @param root
     * @param result
     */
    public Map<Integer, List<TreeNode>> bfsLevel(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return null;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        // 层级信息
        int level = 0;
        // 当前层的元素有哪些
        Map<Integer, List<TreeNode>> levelNodes = new HashMap<>();

        while (!deque.isEmpty()) {
            int size = deque.size();
            level++;
            List<TreeNode> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.pop();
                nodes.add(poll);
                result.add(poll.val);
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
            levelNodes.put(level, nodes);
        }
        return levelNodes;
    }

    /**
     * 广度优先遍历
     * 队列 + 层级
     *
     * @param root
     * @param result
     */
    public int bfsLevel2(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        // 层级信息
        int level = 0;
        // 当前层的元素有哪些
        // 当前层的结束节点
        TreeNode curEnd = root;
        // 下一层的结束节点
        TreeNode nextEnd = null;

        while (!deque.isEmpty()) {

            TreeNode cur = deque.pop();
            result.add(cur.val);

            if (cur.left != null) {
                deque.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                deque.offer(cur.right);
                nextEnd = cur.right;
            }

            if (cur == curEnd) {
                curEnd = nextEnd;
                level++;
            }
        }
        return level;
    }

    /**
     * 广度优先遍历
     * 宽度
     *
     * @param root
     * @param result
     */
    public int bfsLevelMaxWidth(TreeNode root, List<Integer> result) {
        result.clear();
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        // 当前层的结束节点
        TreeNode curEnd = root;
        // 下一层的结束节点
        TreeNode nextEnd = null;
        int max = 0;
        int curWidth = 0;

        while (!deque.isEmpty()) {

            TreeNode cur = deque.pop();
            result.add(cur.val);
            curWidth++;

            if (cur.left != null) {
                deque.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                deque.offer(cur.right);
                nextEnd = cur.right;
            }

            if (cur == curEnd) {
                curEnd = nextEnd;
                max = Math.max(curWidth, max);
                curWidth = 0;
            }
        }
        return max;
    }

    /**
     * 广度优先遍历
     * 包含 null 的宽度
     * int, 对节点进行编号
     *
     * @param root
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        // 存储每一层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 存储节点编号
        Queue<Integer> qIndex = new LinkedList<>();

        queue.offer(root);
        qIndex.offer(0);

        int max = 0, left = 0, right = 0;
        while (!queue.isEmpty()) {

            int len = queue.size();

            for (int i = 0; i < len; i++) {

                root = queue.poll();
                int index = qIndex.poll();

                if (i == 0) left = index;
                if (i == len - 1) right = index;

                if (root.left != null) {
                    queue.offer(root.left);
                    qIndex.offer(2 * index + 1);
                }

                if (root.right != null) {
                    queue.offer(root.right);
                    qIndex.offer(2 * index + 2);
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }


    /**
     * 创建树
     *
     * @return
     */
    public TreeNode createRoot() {
        TreeNode node0 = new TreeNode(2, null, null);
        //TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);

        TreeNode node3 = new TreeNode(7, node2, null);
        //TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(6, null, null);
        TreeNode node6 = new TreeNode(7, null, null);
        TreeNode node7 = new TreeNode(6, null, node0);

        TreeNode node8 = new TreeNode(8, node5, node3);
        TreeNode node9 = new TreeNode(8, node6, node7);

        TreeNode root = new TreeNode(0, node8, node9);
        return root;
    }

    /**
     * 打印结果
     *
     * @param result
     * @param name
     */
    public void print(List<Integer> result, String name) {
        System.out.println(name);
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }

    public void print(List<TreeNode> result) {
        Iterator<TreeNode> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().val + "\t");
        }
        System.out.println();
    }

    public void print(Map<Integer, List<TreeNode>> result) {
        Set<Integer> keySet = result.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println("第 " + key + " 层包含元素：");
            List<TreeNode> treeNodes = result.get(key);
            print(treeNodes);
        }
    }


    public static void main(String[] args) {
        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();
        TreeNode root = binaryTreeTraversal.createRoot();
        List<Integer> result = new ArrayList<>();
        binaryTreeTraversal.preOrderTraversalRecursive(root, result);
        binaryTreeTraversal.print(result, "前序遍历 -- 递归模式");
        binaryTreeTraversal.preOrderTraversalNonRecursive(root, result);
        binaryTreeTraversal.print(result, "前序遍历 -- 非递归模式");
        result.clear();
        binaryTreeTraversal.inOrderTraversalRecursive(root, result);
        binaryTreeTraversal.print(result, "中序遍历 -- 递归模式");
        binaryTreeTraversal.inOrderTraversalNonRecursive(root, result);
        binaryTreeTraversal.print(result, "中序遍历 -- 非递归模式");
        binaryTreeTraversal.inOrderTraversalNonRecursive2(root, result);
        binaryTreeTraversal.print(result, "中序遍历 -- 非递归模式2");
        result.clear();
        binaryTreeTraversal.posOrderTraversalRecursive(root, result);
        binaryTreeTraversal.print(result, "后序遍历 -- 递归模式");
        result.clear();
        binaryTreeTraversal.posOrderTraversalNonRecursive(root, result);
        binaryTreeTraversal.print(result, "后序遍历 -- 非递归模式 -- 双栈");
        binaryTreeTraversal.posOrderTraversalNonRecursive2(root, result);
        binaryTreeTraversal.print(result, "后序遍历 -- 非递归模式 -- 单栈 + 双引用");

        binaryTreeTraversal.bfs(root, result);
        binaryTreeTraversal.print(result, "层级遍历 -- bfs");

        binaryTreeTraversal.bfs2(root, result);
        binaryTreeTraversal.print(result, "层级遍历 -- bfs2");

        Map<Integer, List<TreeNode>> resultMap = binaryTreeTraversal.bfsLevel(root, result);
        binaryTreeTraversal.print(result, "层级遍历 -- bfsLevel");
        binaryTreeTraversal.print(resultMap);

        int level = binaryTreeTraversal.bfsLevel2(root, result);
        binaryTreeTraversal.print(result, "层级遍历 -- bfsLevel2");
        System.out.println("共有 " + level + " 层");

        int maxWidth = binaryTreeTraversal.bfsLevelMaxWidth(root, result);
        System.out.println("最大宽度是 " + maxWidth);
        int widthOfBinaryTree = binaryTreeTraversal.widthOfBinaryTree(root);
        System.out.println("最大宽度是 " + widthOfBinaryTree);
    }

}

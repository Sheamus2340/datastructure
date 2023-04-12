package org.sheamus.datastructure.tree.common;


import java.util.*;

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

    /**
     * 广度优先遍历
     *
     * @param root
     */
    public Map<Integer, Integer> bfsForEach(TreeNode root) {
        Map<Integer, Integer> result = new HashMap<>();
        if (root == null) {
            result.put(0, 0);
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        Integer level = 0;

        while (deque.size() != 0) {
            int size = deque.size();
            ++level;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                System.out.print(poll.data + "\t");
                result.put(level, i + 1);

                if (poll.leftNode != null) {
                    deque.offer(poll.leftNode);
                }

                if (poll.rightNode != null) {
                    deque.offer(poll.rightNode);
                }
            }
        }
        return result;
    }

    /**
     * 广度优先遍历
     *
     * @param root
     */
    public Map<Integer, List<TreeNode>> bfsForEachNodes(TreeNode root) {
        // 存放 层级，层级对应的节点元素集合 的映射
        Map<Integer, List<TreeNode>> result = new HashMap<>();
        if (root == null) {
            result.put(0, null);
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        // 存放当前是第几层的变量
        Integer level = 0;

        while (deque.size() != 0) {
            int size = deque.size();
            ++level;
            List<TreeNode> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();

                System.out.print(poll.data + "\t");
                levelNodes.add(poll);

                if (poll.leftNode != null) {
                    deque.offer(poll.leftNode);
                    if (poll.rightNode == null) {
                        TreeNode nullNode = new TreeNode();
                        deque.offer(nullNode);
                    }
                }

                if (poll.rightNode != null) {
                    if (poll.leftNode == null) {
                        TreeNode nullNode = new TreeNode();
                        deque.offer(nullNode);
                    }
                    deque.offer(poll.rightNode);
                }
            }
            result.put(level, levelNodes);
        }
        return result;
    }

    /**
     * 递归的思路：
     * 1. 找整个递归的终止条件：递归应该在什么时候结束？
     * 2. 找返回值：应该给上一级返回什么信息？
     * 3. 本级递归应该做什么：在这一级递归中，应该完成什么任务？
     *
     * 求二叉树的最大深度
     * 1. 找终止条件。什么情况下递归结束？
     * 当然是树为空的时候，此时树的深度为0，递归就结束了。
     *
     * 2.找返回值。应该返回什么？题目求的是树的最大深度，
     * 我们需要从每一级得到的信息自然是当前这一级对应的树的最大深度，
     * 因此我们的返回值应该是当前树的最大深度，这一步可以结合第三步来看。
     *
     * 3.本级递归应该做什么。首先，还是强调要走出之前的思维误区，递归后我们眼里的树一定是这个样子的，
     * 看下图。此时就三个节点：root、rootleft、rootright，其中根据第二步，rootleft和rootright分别记录的是root的左右子树的最大深度。那么本级递归应该做什么就很明确了，
     * 自然就是在root的左右子树中选择较大的一个，再加上1就是以root为根的子树的最大深度了，然后再返回这个深度即可。
     *
     * @param root
     */
    public int treeDeeps(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeeps = treeDeeps(root.leftNode);
        int rightDeeps = treeDeeps(root.rightNode);
        return Math.max(leftDeeps, rightDeeps) + 1;
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
        System.out.println("\n广度优先遍历--foreach模式");
        Map<Integer, Integer> result = treeMaintain.bfsForEach(root);
        System.out.println();
        result.forEach((key, value) -> {
            System.out.println("第" + key + "层，元素个数是：" + value);
        });
        System.out.println("\n广度优先遍历--foreach模式--Node节点");
        Map<Integer, List<TreeNode>> bfsForEachNodes = treeMaintain.bfsForEachNodes(root);
        System.out.println();
        bfsForEachNodes.forEach((key, value) -> {
            System.out.println("第" + key + "层，元素个数是：" + value.size() + ", 元素包含：");
            ArrayList<TreeNode> treeNodes = (ArrayList<TreeNode>) value;
            treeNodes.forEach((node) -> {
                int data = node.data;
                if (data == Integer.MIN_VALUE) {
                    System.out.print("\t\t");
                } else {
                    System.out.print(data + "\t");
                }
            });
            System.out.println();
        });
        System.out.print("\n二叉树的最大深度--递归: ");
        System.out.println(treeMaintain.treeDeeps(root));
    }


}

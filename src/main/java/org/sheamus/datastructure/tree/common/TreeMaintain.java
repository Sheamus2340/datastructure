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
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
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
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    // 中序遍历
    // 递归方式
    public void inOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(root.left);
        System.out.print(root.data + "\t");
        inOrderRecursive(root.right);
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
                curNode = curNode.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.print(node.data + "\t");
                curNode = node.right;
            }
        }
    }

    /**
     * 实现思路
     * 使用一个栈和一个辅助引用：
     * 核心思想是看成左节点的遍历
     *  1和2的区别：
     *  2没有子问题的概念，巧妙使用 cur 引用变量
     *
     * @param root
     */
    public void inOrderNotRecursive2(TreeNode root) {
        if (root == null) {
            return;
        }
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

    // 后序遍历
    // 递归方式
    public void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.data + "\t");
    }

    // 非递归模式

    /**
     * 双栈实现
     * 第一个栈的打印顺序是
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
        // 缓存节点数据
        Stack<TreeNode> stackReverse = new Stack<>();

        stack.push(root);
        TreeNode cur;

        while (!stack.isEmpty()) {
            cur = stack.pop();
            stackReverse.push(cur);

            if (cur.left != null) {
                stack.push(cur.left);
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        while (!stackReverse.isEmpty()) {
            cur = stackReverse.pop();
            System.out.print(cur.data + "\t");
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
        TreeNode cur = root;
        // 处理过的节点
        TreeNode preNode = null;
        while (cur != null || !stack.isEmpty()) {
            //每次先找到最左边的节点
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            //弹出栈顶
            TreeNode node = stack.pop();
            //如果该元素的右边没有或是已经访问过
            if (node.right == null || node.right == preNode) {
                //访问中间的节点
                System.out.print(node.data + "\t");
                //且记录为访问过了
                preNode = node;
            } else {
                //该节点入栈
                stack.push(node);
                //先访问右边
                cur = node.right;
            }
        }
    }

    /**
     * 双指针实现后序遍历
     *
     * @param root
     */
    public void postOrderNotRecursiveDoublePoint2(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // 表示上次操作的元素的位置，即打印的节点
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            // 当前操作的节点
            TreeNode cur = stack.peek();
            // 一共需要处理7种情况：
            /**
             * 1. 左、右节点都是空，那么弹出打印当前节点打印； - 3
             * 2. 左节点为空，右节点不为空，并且上次处理节点不是右节点，那么将右节点入栈； - 2
             * 3. 左节点不为空，并且上次处理节点不是左节点也不是右节点，那么将左节点入栈； - 1
             * 4. 左节点不为空，右节点为空，并且上次处理节点是左节点，那么将右节点入栈； - 2
             * 5. 左右节点都不为空，并且上次处理节点不是左节点也不是右节点，那么将左节点入栈； - 1
             * 6. 左右节点都不为空，并且上次处理节点是左节点，那么将右节点入栈； - 2
             * 7. 左右节点都不为空，并且上次处理节点是右节点，那么弹出打印当前节点打印；- 3
             *
             * 处理结果的汇总：
             * 1. 左子点入栈：
             *    条件是：左节点不为空，并且上次处理节点不是左节点 || 左右节点都不为空，并且上次处理节点不是左节点也不是右节点
             * 2. 右节点入栈：
             *    条件是：左节点为空，右节点不为空，并且上次处理节点不是右节点 || 左节点不为空，右节点为空，并且上次处理节点是左节点 || 左右节点都不为空，并且上次处理节点是左节点
             * 3. 打印节点：
             *    条件是：左、右节点都是空 || 左右节点都不为空，并且上次处理节点是右节点
             */
            if (cur.left != null && pre != cur.left && pre != cur.right) { // 左节点没有处理
                stack.push(cur.left);
            } else if (cur.right != null && pre != cur.right) { // 右节点没有处理
                stack.push(cur.right);
            } else { // 处理当前节点
                // 弹出即打印
                TreeNode pop = stack.pop();
                System.out.print(pop.data + "\t");
                // 标记上次处理的节点
                pre = cur;
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

                if (poll.left != null) {
                    deque.offer(poll.left);
                }

                if (poll.right != null) {
                    deque.offer(poll.right);
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
            List<TreeNode> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();

                System.out.print(poll.data + "\t");
                levelNodes.add(poll);

                if (poll.left != null) {
                    deque.offer(poll.left);
                    if (poll.right == null) {
                        TreeNode nullNode = new TreeNode();
                        deque.offer(nullNode);
                    }
                }

                if (poll.right != null) {
                    if (poll.left == null) {
                        TreeNode nullNode = new TreeNode();
                        deque.offer(nullNode);
                    }
                    deque.offer(poll.right);
                }
            }
            result.put(++level, levelNodes);
        }
        return result;
    }

    /**
     * 递归的思路：
     * 1. 找整个递归的终止条件：递归应该在什么时候结束？
     * 2. 找返回值：应该给上一级返回什么信息？
     * 3. 本级递归应该做什么：在这一级递归中，应该完成什么任务？
     * <p>
     * 求二叉树的最大深度
     * 1. 找终止条件。什么情况下递归结束？
     * 当然是树为空的时候，此时树的深度为0，递归就结束了。
     * <p>
     * 2.找返回值。应该返回什么？题目求的是树的最大深度，
     * 我们需要从每一级得到的信息自然是当前这一级对应的树的最大深度，
     * 因此我们的返回值应该是当前树的最大深度，这一步可以结合第三步来看。
     * <p>
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
        int leftDeeps = treeDeeps(root.left);
        int rightDeeps = treeDeeps(root.right);
        return Math.max(leftDeeps, rightDeeps) + 1;
    }

    /**
     * mirror版实现遍历
     * Morris中序遍历
     * 中序遍历
     * 按照定义，在中序遍历中，对于一棵以root为根的二叉树，当访问完root的前驱节点后，需要回到root节点进行访问，然后再到root的右儿子进行访问。于是，我们可以每次访问到一棵子树时，找到它的前驱节点，把前驱节点的右儿子变为当前的根节点root，这样当遍历完前驱节点后，可以顺着这个右儿子回到根节点root。
     * <p>
     * 但问题是修改了该前驱节点的右儿子后什么时候再改回来呢？
     * <p>
     * 当第一次访问以root为根的子树时，找到它的前驱pre，此时pre的右儿子必定为空，于是把这个右儿子设置为root，以便以后根据这个指针回到root节点。
     * 当第二次回到以root为根的子树时，再找到它的前驱pre，此时pre的右儿子已经被设置成了当前的root，这时把该右儿子重新设置成NULL，然后继续进行root的右儿子的遍历。于是完成了指针的修改。
     * 在这样的情景下，寻找当前节点的前驱节点时，不仅需要判断其是否有右儿子，而且还要判断右儿子是否为当前的root节点，跟普通情况下的寻址前驱节点稍微多了一个条件。
     * <p>
     * 由于在每次遍历一个节点的时候都需要寻找其前驱节点，而寻找前驱节点的时间一般与树的高度相关，这样看上去算法的复杂度应该为O(nlogn)才对。但由于其只需要对有左儿子的节点才寻找前驱，于是所有寻找前驱时走过的路加起来至多为一棵树的节点数，例如在下文的例子中，只需要对以下节点寻找前驱：
     * <p>
     * 节点4：寻找路径为：2-3
     * 节点2：寻找路径为：1
     * 节点6：寻找路径为：5
     * 于是寻找前驱加上遍历的运算量之和至多为2*n，n为节点个数，于是算法的复杂度为仍然为O(n)。
     * -----------------------------------
     * 二叉树遍历（递归、非递归、mirror）转
     * https://blog.51cto.com/u_15064650/4205862
     */
    public void inOrderMirrorNonRecursive(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.data + "\t");
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) { //第一次访问，修改pre的右儿子
                    pre.right = cur;
                    cur = cur.left;
                } else {                  //第二次访问，改回pre的右儿子
                    pre.right = null;
                    System.out.print(cur.data + "\t");
                    cur = cur.right;
                }
            }
        }
    }

    public void preOrderMirrorNonRecursive(TreeNode root) {
        if (root == null)
            return;
        TreeNode cur = root;
        TreeNode temp = null;
        while (cur != null) {
            if (cur.left == null) {
                //左子树为空
                System.out.print(cur.data + "\t");
                cur = cur.right;
            } else {
                temp = cur.left;
                //左子树不为空
                while (temp.right != null && temp.right != cur) {
                    temp = temp.right;
                }
                if (temp.right == cur) {
                    temp.right = null;
                    cur = cur.right;
                } else {
                    System.out.print(cur.data + "\t");
                    temp.right = cur;
                    cur = cur.left;
                }

            }
        }
    }


    // 广度遍历
    public static void main(String[] args) {
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


        TreeMaintain treeMaintain = new TreeMaintain();
        System.out.println("前序遍历--递归模式");
        treeMaintain.preOrderRecursive(root);
        System.out.println("\n前序遍历--非递归模式");
        treeMaintain.preOrderNotRecursive(root);
        System.out.println("\n前序遍历--非递归模式-mirror");
        treeMaintain.preOrderMirrorNonRecursive(root);
        System.out.println("\n中序遍历--递归模式");
        treeMaintain.inOrderRecursive(root);
        System.out.println("\n中序遍历--非递归模式");
        treeMaintain.inOrderNotRecursive(root);
        System.out.println("\n中序遍历--非递归模式-2");
        treeMaintain.inOrderNotRecursive2(root);
        System.out.println("\n中序遍历--非递归模式-mirror模式");
        treeMaintain.inOrderMirrorNonRecursive(root);
        System.out.println("\n后序遍历--递归模式");
        treeMaintain.postOrderRecursive(root);
        System.out.println("\n后序遍历--非递归模式-双栈实现");
        treeMaintain.postOrderNotRecursiveDoubleStack(root);
        System.out.println("\n后序遍历--非递归模式-双指针实现");
        treeMaintain.postOrderNotRecursiveDoublePoint(root);
        System.out.println("\n后序遍历--非递归模式-双指针实现2");
        treeMaintain.postOrderNotRecursiveDoublePoint2(root);
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

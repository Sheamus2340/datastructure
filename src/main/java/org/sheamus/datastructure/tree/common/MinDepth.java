package org.sheamus.datastructure.tree.common;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }

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
        MinDepth minDepth = new MinDepth();
        int i = minDepth.minDepth(root);
        System.out.println(i);
    }

}

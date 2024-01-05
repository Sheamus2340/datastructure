package org.sheamus.learn.top100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        // bfs
        Deque<TreeNode> deque = new LinkedList<>();

        int level = 0;
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }

                if (poll.right != null) {
                    deque.offer(poll.right);
                }

            }

        }
        return level;
    }

}

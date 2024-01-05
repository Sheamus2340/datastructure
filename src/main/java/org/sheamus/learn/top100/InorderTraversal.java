package org.sheamus.learn.top100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/?envType=study-plan-v2&envId=top-100-liked
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    /**
     * 中序遍历
     * 左、中、右
     *
     * @param root
     * @param res
     */
    private void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, res);
        res.add(root.val);
        inorderTraversal(root.right, res);
    }

    /**
     * 非递归方式
     *
     * @param root
     * @param res
     */
    private void inorderNoTraversal(TreeNode root, List<Integer> res) {

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
                res.add(pop.val);
                cur = pop.right;
            }
        }

    }


}

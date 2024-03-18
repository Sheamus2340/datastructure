package org.sheamus.struct.recursion;

/**
 * title：https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallest {

    int rink = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        travese(root, k);
        return res;

    }

    private void travese(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        travese(root.left, k);

        rink++;
        if (rink == k) {
            res = root.val;
            return;
        }

        travese(root.right, k);
    }

}

package org.sheamus.struct.recursion;

/**
 * title：
 */
public class Flatten {

    // 定义：将以 root 为根的树拉平为链表
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;
        // 利⽤定义，把左右⼦树拉平
        flatten(root.left);
        flatten(root.right);
        /**** 后序遍历位置 ****/
        // 1、左右⼦树已经被拉平成⼀条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左⼦树作为右⼦树
        root.left = null;
        root.right = left;
        // 3、将原先的右⼦树接到当前右⼦树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


}

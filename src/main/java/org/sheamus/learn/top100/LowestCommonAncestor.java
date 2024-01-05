package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 分情况
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right; //
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    class TreeNodeParent {
        int val;
        public TreeNodeParent left;
        public TreeNodeParent right;
        public TreeNodeParent parent;
    }

    /**
     * 带有指向父节点的指针的树
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNodeParent lowestCommonParent(TreeNodeParent root, TreeNodeParent p, TreeNodeParent q) {
        if (p == null || q == null) return null;
        TreeNodeParent pA = p, pB = q;
        while (pA != pB) {
            pA = pA == null ? q : pA.parent;
            pB = pB == null ? p : pB.parent;
        }
        return pA;
    }


}

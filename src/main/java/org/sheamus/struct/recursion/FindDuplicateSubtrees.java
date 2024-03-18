package org.sheamus.struct.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * title：https://leetcode.cn/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {

    List<TreeNode> res = new ArrayList<>();

    Map<String, Integer> dic = new HashMap<>();

    /**
     * 遍历的思维
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    public String traverse(TreeNode root) {
        if (root == null) {
            return " ";
        }

        String key = root.val + "_" +
                traverse(root.left) +
                traverse(root.right);
        dic.put(key, dic.getOrDefault(key, 0) + 1);

        // 这里必须是等于就放进去，要不然有些大于2的子树会重复放入这个集合中
        if (dic.get(key) == 2) {
            res.add(root);
        }
        return key;
    }

}

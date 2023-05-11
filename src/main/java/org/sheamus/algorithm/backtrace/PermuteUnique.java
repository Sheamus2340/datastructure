package org.sheamus.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PermuteUnique {

    public List<List<Integer>> permute2(int[] nums) {
        // int depth : 遍历的深度
        // Stack path : 遍历的路径，考虑到时一个先进后出的结构，因此使用栈的结构实现；
        // boolean[] used : 标识索引对应的元素是否使用了
        // int len : 数组的长度
        // int[] nums : 数组
        // List<List<Integer>> res: 结果
        int len = nums.length;
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];

        // 排序是剪枝的前提
        Arrays.sort(nums);

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param nums
     * @param len
     * @param depth
     * @param path
     * @param used
     * @param res
     */
    public void dfs(int[] nums, int len, int depth,
                    Stack<Integer> path,
                    boolean[] used,
                    List<List<Integer>> res) {
        if (depth == len) {
            // 构造结果
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            // 选择一个没有使用的元素
            if (used[i])
                continue;

            // 剪枝条件
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // path 元素的添加；used 的状态的改变；
            path.push(nums[i]);
            used[i] = true;
            // 递归的遍历下一个元素
            dfs(nums, len, depth + 1, path, used, res);
            // 完成之后进行回溯
            path.pop();
            used[i] = false;

        }
    }


}

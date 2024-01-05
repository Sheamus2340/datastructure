package org.sheamus.learn.top100;

import java.util.*;

/**
 * https://leetcode.cn/problems/permutations/?envType=study-plan-v2&envId=top-100-liked
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int depth = 0;
        Stack<Integer> path = new Stack<>();
        boolean[] used = new boolean[len];

        dfs(nums, len, depth, path, used, res);

        return res;
    }

    private void dfs(int[] nums, int len, int depth, Stack<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.push(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                path.pop();
                used[i] = false;
            }
        }

    }
}

package org.sheamus.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

public class PermuteTest {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int depth = 0;
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, depth, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 终止条件
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历
        for (int i = 0; i < len; i++) {
            // 非进过的路径
            if (!used[i]) {
                // 递归
                path.add(nums[i]);
                used[i] = true;
                // 回溯
                dfs(nums, len, depth + 1, path, used, res);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        PermuteTest solution = new PermuteTest();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }

}

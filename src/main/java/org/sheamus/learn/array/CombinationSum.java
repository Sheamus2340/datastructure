package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * title：https://leetcode.cn/problems/combination-sum/?envType=study-plan-v2&envId=top-100-liked
 */
public class CombinationSum {

    /**
     * backtrace
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        // 排序方便剪枝
        Arrays.sort(candidates);
        // 方便排除之前的选择
        int start = 0;
        dfs(candidates, start, path, target, res);
        return res;
    }

    private void dfs(int[] candidates, int start, Stack<Integer> path, int target, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝 1，当结果已经超过目标值
            if (target - candidates[i] < 0) {
                break;
            }
            // 剪枝2，减去两个相邻相同值
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.push(candidates[i]);
            dfs(candidates, i, path, target - candidates[i], res);
            path.pop();
        }
    }

    public static void main(String[] args) {

    }


}

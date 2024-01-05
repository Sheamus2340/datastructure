package org.sheamus.learn.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/combination-sum/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        int index = 0;
        Arrays.sort(candidates);
        Stack<Integer> path = new Stack<>();
        dfs(candidates, index, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int index, int len, int target, Stack<Integer> path, List<List<Integer>> res) {

        if (sum(path) == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            // 剪枝
            if (sum(path) < target) {
                path.push(candidates[i]);
                dfs(candidates, i, len, target, path, res);
                path.pop();
            }
        }

    }

    private int sum(Stack<Integer> path) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum;
    }

}

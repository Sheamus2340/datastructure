package org.sheamus.algorithm.backtrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSum3 {

    // 不能重复
    int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int len = candidates.length;
    List<List<Integer>> res = new ArrayList<>();
    int size = 0;

    public List<List<Integer>> combinationSum(int k, int n) {

        // 目标值
        int target = n;
        // 选 k 个数
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        size = k;

        dfs(0, target, used, path);

        return res;
    }

    private void dfs(int begin, int target, boolean[] used, Deque<Integer> path) {
        if (target == 0 && path.size() == size) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 剪枝1：去除超过目标值
            if (target - candidates[i] < 0) {
                break;
            }

            // 剪枝2， 去除大小大于 k 的不必要计算
            if (path.size() > size) {
                break;
            }

            if (!used[i]) {
                path.addLast(candidates[i]);
                used[i] = true;
                dfs(i + 1,target - candidates[i], used, path);
                path.removeLast();
                used[i] = false;
            }
        }
    }

    private void print(List<List<Integer>> res) {
        for (List<Integer> r : res) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum = new CombinationSum3();

        List<List<Integer>> lists = combinationSum.combinationSum(3, 7);
        combinationSum.print(lists);

    }

}

package org.sheamus.algorithm.backtrace;

import java.util.*;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先排序，方便后面剪枝
        Arrays.sort(candidates);
        int len = candidates.length;
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();

        dfs(candidates, 0, len, used, target, path, res);

        return res;
    }

    private void dfs(int[] candidates, int begin, int len, boolean[] used, int target, Deque<Integer> path, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 剪枝1 ：减掉不必要的计算
            if (target - candidates[i] < 0) {
                break;
            }
            
            // 剪枝2 ：减掉重复的数字
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                // path, used
                path.addLast(candidates[i]);
                used[i] = true;
                dfs(candidates, i, len, used, target - candidates[i], path, res);
                path.removeLast();
                used[i] = false;
            }
        }

    }

    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        // 先排序，方便后面剪枝
        Arrays.sort(candidates);
        int len = candidates.length;

        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();

        dfs3(candidates, 0, len, target, path, res);

        return res;
    }

    private void dfs3(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 剪枝1 ：减掉不必要的计算
            if (target - candidates[i] < 0) {
                break;
            }
            // 剪枝2 ：减掉重复的数字
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // path, used
            path.addLast(candidates[i]);
            dfs3(candidates, i + 1, len, target - candidates[i], path, res);
            path.removeLast();

        }

    }


    private void print(List<List<Integer>> res) {
        for (List<Integer> r : res) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        CombinationSum2 combinationSum = new CombinationSum2();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> lists = combinationSum.combinationSum2(candidates, target);
        combinationSum.print(lists);

        System.out.println("-----");

        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};

        List<List<Integer>> lists3 = combinationSum.combinationSum3(candidates2, target);
        combinationSum.print(lists3);

    }

}

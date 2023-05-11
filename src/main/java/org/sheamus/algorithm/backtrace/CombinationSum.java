package org.sheamus.algorithm.backtrace;

import java.util.*;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum {
    /**
     * 回溯算法 + 剪枝
     * candidates = [2,3,6,7], target = 7
     * [[2,2,3],[7]]
     *
     * 7
     * 7-2=5，7-3=4，7-6=1，7-7=0
     * 5-2=3，5-3=2，5-6=-1，5-7=-2，4-2=2，4-3=2，4-6=-2，4-7=-3，1-2=-1，
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 首先先排序，方便后面剪枝
        Arrays.sort(candidates);
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        // 记录 路径
        Stack<Integer> path = new Stack<>();
        // 记录 目标的路径
        Stack<Integer> targetPath = new Stack<>();
        targetPath.push(target);
        dfs(candidates, len, targetPath, path, res);
        return res;
    }

    private void dfs(int[] candidates, int len, Stack<Integer> targetPath, Stack<Integer> path, List<List<Integer>> res) {
        // 终止条件
        Integer target = targetPath.peek();
        if(target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 处理流程
        // target
        // path
        for (int i = 0; i < len; i++) {
            // 剪枝 ：当 path 中元素 大于 将要加入的元素 时，就不需要往下进行了
            if (!path.isEmpty() && path.peek() > candidates[i]) {
                continue;
            }
            int curTarget = target - candidates[i];
            //  剪枝 ：当前的数据已经小于0了，后面就不需要计算了
            if (curTarget < 0) {
                break;
            }
            path.push(candidates[i]);
            targetPath.push(curTarget);
            dfs(candidates, len, targetPath, path, res);
            path.pop();
            targetPath.pop();
        }
    }

    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        // 首先先排序，方便后面剪枝
        Arrays.sort(candidates);
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        // 记录 路径
        Stack<Integer> path = new Stack<>();
        dfs3(candidates, len, target, path, res);
        return res;
    }

    private void dfs3(int[] candidates, int len, int target, Stack<Integer> path, List<List<Integer>> res) {
        // 终止条件
        if(target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 处理流程
        // target
        // path
        for (int i = 0; i < len; i++) {
            // 剪枝 ：当 path 中元素 大于 将要加入的元素 时，就不需要往下进行了
            if (!path.isEmpty() && path.peek() > candidates[i]) {
                continue;
            }
            int curTarget = target - candidates[i];
            //  剪枝 ：当前的数据已经小于0了，后面就不需要计算了
            if (curTarget < 0) {
                break;
            }
            path.push(candidates[i]);
            dfs3(candidates, len, curTarget, path, res);
            path.pop();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs2(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    private void print(List<List<Integer>> res) {
        for (List<Integer> r : res) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        combinationSum.print(lists);
        System.out.println("---");
        List<List<Integer>> lists2 = combinationSum.combinationSum2(candidates, target);
        combinationSum.print(lists2);
        System.out.println("---");
        List<List<Integer>> lists3 = combinationSum.combinationSum3(candidates, target);
        combinationSum.print(lists3);
    }
}

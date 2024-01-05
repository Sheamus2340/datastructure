package org.sheamus.learn.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked
 */
public class SubarraySum {

    /**
     * 回溯 + 剪枝
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        // 深度
        int depth = 0;
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        dfs(nums, k, len, depth, path, used, res);
        return res.size();
    }

    private void dfs(int[] nums, int k, int len, int depth, Stack<Integer> path, boolean[] used, List<List<Integer>> res) {
        int sum = getSum(path);
        if (k == sum && depth <= len) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝
        if (sum > k) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                int num = nums[i];
                path.push(num);
                used[i] = true;
                dfs(nums, k, len, depth + 1, path, used, res);
                path.pop();
                used[i] = false;
            }
        }
    }

    private int getSum(Stack<Integer> path) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        SubarraySum subarraySum = new SubarraySum();
        int sum = subarraySum.subarraySum(nums, k);
        System.out.println(sum);
    }
}

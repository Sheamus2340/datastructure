package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * title：https://leetcode.cn/problems/3sum/
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if (nums[i] > 0) {
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (target - sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    // 再将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    // 如果重复，直接跳过。
                    // 去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    // 将 左指针右移，将右指针左移。
                    start++;
                    end--;
                } else if (sum > target)
                    end--;
                else start++;
            }
        }
        return res;
    }

    /**
     * 设置目标值，次数
     *
     * @param nums
     * @param target
     * @param times
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums, int target, int times) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序，方便剪枝
        Arrays.sort(nums);
        Stack<Integer> path = new Stack<>();
        // 方便不遍历到已经选择的数字
        int start = 0;
        dfs(nums, start, times, path, target, res);
        return res;
    }

    private void dfs(int[] nums, int start, int times, Stack<Integer> path, int target, List<List<Integer>> res) {
        if (times == 0 && target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (path.size() > 3) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 剪枝5，结果不为0
            if (times == 0 && target != 0) {
                break;
            }

            // 剪枝3，如果第一个数字大于0结束
            if (i == start && path.isEmpty() && nums[i] > 0) {
                break;
            }

            // 剪枝1，如果结果大于0，那么直接结束当前
            if (target + nums[i] > 0) {
                break;
            }

            // 剪枝4，如果相邻的数字相同 i > start && candidates[i] == candidates[i - 1]
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            path.push(nums[i]);
            dfs(nums, i + 1, times - 1, path, target + nums[i], res);
            path.pop();
        }

    }


    /**
     * backtrace + pruning（remove duplicates）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        int start = 0;
        Arrays.sort(nums);
        dfs(nums, start, path, res);
        return res;
    }

    private void dfs(int[] nums, int start, Stack<Integer> path, List<List<Integer>> res) {
        if (path.size() == 3 && isZero(path)) {
            ArrayList<Integer> list = new ArrayList<>(path);
            res.add(list);
            return;
        }

        // 定义 start 的目的是让每一轮选择都自动排除原来的序号
        for (int i = start; i < nums.length; i++) {
            if (sum(path) > 0) {
                continue;
            }
            // 首个数字大于0直接结束
            if (path.isEmpty() && nums[i] > 0) {
                continue;
            }
            // 如果第一数字在原来结果中存在不执行
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            path.push(nums[i]);
            dfs(nums, i + 1, path, res);
            path.pop();
        }
    }

    private boolean isZero(Stack<Integer> path) {
        return sum(path) == 0;
    }

    private int sum(Stack<Integer> path) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum;
    }

    public void print(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums, 0, 3);
        threeSum.print(lists);
    }

}

package org.sheamus.datastructure.array.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class TwoSumSolution {

    /**
     * 解决方案：
     * 1. 暴力求解，2层循环
     * 2. 排序之后，双指针
     * 3. 借助 Map 求值
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // 存储值 和 索引
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if (map.containsKey(res)) {
                return new int[]{map.get(res), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSumSolution twoSumSolution = new TwoSumSolution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] res = twoSumSolution.twoSum(nums, target);
        for (int r : res) {
            System.out.println(r);
        }
    }

}

package org.sheamus.learn.top100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked
 */
public class TwoSum {

    /**
     * 2,7,11,15    9
     * <p>
     * 解法一：暴力
     *
     * 解法二：HashMap
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        Map<Integer, Integer> dic = new HashMap<>();
        dic.put(nums[0], 0);
        for (int i = 1; i < len; i++) {
            if (dic.containsKey(target - nums[i])) {
                res[0] = dic.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            dic.put(nums[i], i);
        }

        return res;
    }


    public void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum twoSum = new TwoSum();
        int[] res = twoSum.twoSum(nums, target);
        twoSum.print(res);

        int[] res2 = twoSum.twoSum2(nums, target);
        twoSum.print(res2);

    }
}

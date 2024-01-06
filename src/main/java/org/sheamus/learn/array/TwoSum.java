package org.sheamus.learn.array;

import java.util.HashMap;

/**
 * title：https://leetcode.cn/problems/two-sum/description/
 */
public class TwoSum {

    /**
     * two solutions:
     * 1. Exhaustive violence；
     * 2. using hashmap
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int len = nums.length;

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

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        int len = nums.length;
        // key: num  value: index
        HashMap<Integer, Integer> dic = new HashMap<>();

        for (int i = 0; i < len; i++) {
            if (dic.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = dic.get(target - nums[i]);
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
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum twoSum = new TwoSum();
        int[] sum = twoSum.twoSum(nums, target);
        twoSum.print(sum);
    }

}

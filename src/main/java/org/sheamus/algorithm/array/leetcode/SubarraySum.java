package org.sheamus.algorithm.array.leetcode;

/**
 * title：https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked
 */
public class SubarraySum {

    /**
     * 回溯算法
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += sumEquals(nums, i, k);
        }
        return res;
    }

    private int sumEquals(int[] nums, int i, int k) {
        int len = nums.length;
        int num = 0;
        int sum = 0;
        for (int j = i; j < len; j++) {
            if ((sum += nums[j]) == k) {
                num++;
            }
        }
        return num;
    }


}

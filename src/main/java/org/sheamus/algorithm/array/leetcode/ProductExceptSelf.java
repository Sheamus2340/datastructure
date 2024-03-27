package org.sheamus.algorithm.array.leetcode;

/**
 * title：https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class ProductExceptSelf {

    /**
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        // 不包含当前元素的左边所有数的乘积
        int[] left = new int[len];
        // 不包含当前元素的右边所有数的乘积
        int[] right = new int[len];

        left[0] = 1;
        right[len - 1] = 1;

        // 计算左边
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        // 计算右边
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        // 计算结果
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

}

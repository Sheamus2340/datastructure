package org.sheamus.learn.top100;

/**
 * title：https://leetcode.cn/problems/maximum-product-subarray/
 */
public class MaxProduct {

    public static int maxProduct(int[] nums) {

        // 定义变量 maxProduct 用于保存最大乘积
        int maxProduct = Integer.MIN_VALUE;
        // 定义变量 left 和 right 用于滑窗的左边界和右边界
        int left = 0;
        int right = 0;
        // 定义变量 maxProductInWindow 用于保存当前滑窗内的最大乘积
        int maxProductInWindow = 1;

        while (right < nums.length) {
            // 扩大滑窗
            maxProductInWindow *= nums[right++];
            // 更新最大乘积
            maxProduct = Math.max(maxProduct, maxProductInWindow);
            // 缩小滑窗
            while (nums[left] == 0 && left < right) {
                maxProductInWindow /= nums[left++];
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums = {2, -3, -4, 5};
        int maxProduct = maxProduct(nums);
        System.out.println("最大乘积为：" + maxProduct);
    }
}
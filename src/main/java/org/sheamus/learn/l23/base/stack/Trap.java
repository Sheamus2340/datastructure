package org.sheamus.learn.l23.base.stack;

/**
 * 接雨水问题
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">...</a>
 */
public class Trap {
    /**
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (height[left] <= leftMax) {
                sum += leftMax - height[left];
                left++;
            }

            if (height[right] <= rightMax) {
                sum += rightMax - height[right];
                right++;
            }

        }

        return sum;
    }
}

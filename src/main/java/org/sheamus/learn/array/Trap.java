package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/trapping-rain-water/
 */
public class Trap {

    /**
     * 中心扩散法
     *
     * @param heights
     * @return
     */
    public int trap(int[] heights) {
        int len = heights.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                leftMax[i] = heights[i];
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                rightMax[i] = heights[i];
            } else {
                rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
            }
        }

        int sum = 0;

        for (int i = 0; i < len; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            sum += Math.max(min - heights[i], 0);
        }
        return sum;
    }

}

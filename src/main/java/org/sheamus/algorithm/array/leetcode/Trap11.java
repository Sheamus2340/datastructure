package org.sheamus.algorithm.array.leetcode;

public class Trap11 {

    /**
     * int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
     *
     * @param heights
     * @return
     */
    public int trap(int[] heights) {
        // 双指针实现雨水数量
        // 求出当前接雨水的数量，即为 当前节点 左边和右边最大值中较小的高度差
        int[] leftMax = new int[heights.length];
        int[] rightMax = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            if (i == 0) {
                leftMax[i] = heights[i];
                continue;
            }
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        }

        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights.length - 1 == i) {
                rightMax[i] = heights[i];
                continue;
            }
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }

        int sum = 0;

        for (int i = 0; i < heights.length; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            sum += heights[i] - min >= 0 ? 0 : min - heights[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        //int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] heights = {4, 2, 0, 3, 2, 5};
        Trap11 trap11 = new Trap11();
        int trap = trap11.trap(heights);
        System.out.println(trap);
    }

}

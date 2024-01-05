package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked
 */
public class Trap {

    /**
     * 分别求当前位置的左右侧的最大存储在一个数组中，然后比较左右侧最值的较小值计算当前列的雨水
     *
     * @param heights
     * @return
     */
    public int trap(int[] heights) {
        int len = heights.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        fillLeftMax(leftMax, heights);
        fillRightMax(rightMax, heights);

        int sum = 0;

        for (int i = 0; i < len; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            sum += heights[i] - min >= 0 ? 0 : min - heights[i];
        }

        return sum;
    }


    private void fillRightMax(int[] rightMax, int[] heights) {

        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights.length - 1 == i) {
                rightMax[i] = heights[i];
                continue;
            }
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }
    }

    private void fillLeftMax(int[] leftMax, int[] heights) {

        int len = heights.length;

        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                max = Math.max(heights[j], max);
            }
            leftMax[i] = max;
        }
    }


    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Trap trap = new Trap();
        System.out.println(trap.trap(heights));

    }
}

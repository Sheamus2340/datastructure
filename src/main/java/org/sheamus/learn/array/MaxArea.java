package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/container-with-most-water/
 *
 */
public class MaxArea {

    /**
     * double rings
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0, right = len - 1;
        int res = Integer.MIN_VALUE;
        while (left < right) {
            if (height[left] < height[right]) {
                res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
                left++;
            } else {
                res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
                right++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {};
        MaxArea maxArea = new MaxArea();
        int area = maxArea.maxArea(height);
        System.out.println(area);
    }

}

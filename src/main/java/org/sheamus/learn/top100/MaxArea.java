package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = 0;
        while (left < right) {
            int sum = (right - left) * Math.min(height[left], height[right]);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
            area = Math.max(area, sum);
        }
        return area;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        MaxArea maxArea = new MaxArea();
        int area = maxArea.maxArea(heights);
        System.out.println(area);
    }

}

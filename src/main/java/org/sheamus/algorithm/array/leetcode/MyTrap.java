package org.sheamus.algorithm.array.leetcode;

/**
 * 双指针实现
 */
public class MyTrap {

    public int trap(int[] heights) {
        int sum = 0;
        if (heights == null || heights.length == 0) {
            return sum;
        }
        int len = heights.length;
        int left = 0, right = len - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, heights[left]);
            rightMax = Math.max(rightMax, heights[right]);

            if (heights[left] < heights[right]) {
                // leftMax < rightMax
                sum += leftMax - heights[left];
                left++;
            }
            if (heights[left] >= heights[right]) {
                sum += rightMax - heights[right];
                right--;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        MyTrap myTrap = new MyTrap();
        System.out.println(myTrap.trap(heights));
    }

}

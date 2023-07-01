package org.sheamus.algorithm.array.leetcode;

public class TrapTest {

    public int trap(int[] height) {
        // 双指针法
        int len = height.length;
        int left = 0, right = len - 1;
        int leftMax = 0, rightMax = 0;
        int result = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (height[left] < height[right]) {
                result += leftMax - height[left];
                left++;
            }

            if (height[left] >= height[right]) {
                result += rightMax - height[right];
                right--;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrapTest trapTest = new TrapTest();
        System.out.println(trapTest.trap(height));
    }

}

/**
 *  4, 2, 0, 3, 2, 5
 *  i              j
 *  寻找较小的值进行移动
 *     i=4-2=2
 *        i=4-0=4
 *           i=4-3=1
 *             i=4-2=2
 *             9
 */

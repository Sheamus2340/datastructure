package org.sheamus.algorithm.base;

public class MaxArea {

    public int maxArea(int[] height) {
        // 暴力解法 两层for循环 比较最大值

        // 双指针
        int i = 0;
        int j = height.length - 1;
        int max = 0;

        while (i < j) {
            int min = Math.min(height[i], height[j]);
            int x = j - i;
            int sum = min * x;
            if (max < sum) {
                max = sum;
            }
            if (min == height[i]) {
                i++;
            } else {
                j--;
            }
        }
        return max;

    }

    public static void main(String[] args) {

    }

}

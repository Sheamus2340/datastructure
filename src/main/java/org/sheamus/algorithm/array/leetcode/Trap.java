package org.sheamus.algorithm.array.leetcode;

import java.util.Stack;

/**
 * 接雨水
 * https://leetcode.cn/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
 */
public class Trap {

//    public int trap(int[] height) {
//        int len = height.length;
//        int res = 0;
//
//        for (int i = 0; i < len; i++) {
//            // 查询下一个比当前值更大的索引
//            int index = findNextBigNum(height, i);
//            if (index != -1) {
//                res += calc(height, i, index);
//                i = index - 1;
//            }
//        }
//        return res;
//    }
//
//    private int findNextBigNum(int[] nums, int i) {
//        for (int j = i + 1; j < nums.length; j++) {
//            if (nums[j] >= nums[i]) {
//                return j;
//            }
//        }
//        return -1;
//    }
//
//    private int calc(int[] nums, int i, int j) {
//        int min = nums[i] > nums[j] ? nums[j] : nums[i];
//        int res = 0;
//        if (i == (j - 1)) {
//            while ((i - 1) >= 0 && nums[i - 1] >= nums[j]) {
//                min = nums[i - 1] > nums[j] ? nums[j] : nums[i - 1];
//                res += min - nums[i];
//                i--;
//            }
//            return res;
//        }
//        for (int k = i + 1; k < j; k++) {
//            res += min - nums[k];
//        }
//        return res;
//    }

    /**
     * 行的形式
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;

        // 找到最大的高度，以便遍历。
        int max = getMax(height);

        for (int i = 1; i <= max; i++) {

            // 标记是否开始更新 temp
            boolean isStart = false;
            int tempSum = 0;

            for (int j = 0; j < height.length; j++) {

                if (isStart && height[j] < i) {
                    tempSum++;
                }

                if (height[j] >= i) {
                    sum += tempSum;
                    tempSum = 0;
                    isStart = true;
                }

            }
        }
        return sum;
    }

    private int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    /**
     * 列的形式
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum += (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 动态规划
     * max_left[i]: i 左侧的最高高度；
     * max_right[i]: i 右侧的最高高度；
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum += (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap4(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        for (int i = 1; i < len - 1; i++) {
            // leftMax[i] 最大值不包含 height[i]
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        for (int i = len - 2; i >= 0; i--) {
            // rightMax[i] 最大值不包含 height[i]
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (height[i] < min) {
                res += min - height[i];
            }
        }

        return res;
    }

    public int trap6(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 递减栈
     *
     * @param height
     * @return
     */
    public int trap5(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.isEmpty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {0, 7, 1, 4, 6};
        Trap trap = new Trap();
        System.out.println(trap.trap(nums));
        System.out.println(trap.trap2(nums));
        System.out.println(trap.trap3(nums));
        System.out.println(trap.trap4(nums));
        System.out.println(trap.trap5(nums));
        System.out.println(trap.trap6(nums));
    }

}

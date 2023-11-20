package org.sheamus.learn.l23.base.stack;

import java.util.Stack;

/**
 * 下一个更大的数组II
 * <a href="https://leetcode.cn/problems/next-greater-element-ii/">...</a>
 */
public class NextGreaterElementsII {

    /**
     * 1,2,1
     * 2,-1,2
     * 循环数组
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

//        int[] data = new int[len * 2];

//        for (int i = 0; i < data.length; i++) {
//            data[i] = nums[i % len];
//        }

        Stack<Integer> stack = new Stack<>();

        for (int i = len * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % len]) {
                stack.pop();
            }

            if (i < len) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(nums[i % len]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        NextGreaterElementsII nextGreaterElementsII = new NextGreaterElementsII();
        int[] res = nextGreaterElementsII.nextGreaterElements(nums);

        for (int re : res) {
            System.out.print(re + "\t");
        }

    }

}

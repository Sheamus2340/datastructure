package org.sheamus.learn.l23.base.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 下一个更大元素
 * <a href="https://leetcode.cn/problems/next-greater-element-i/description/">...</a>
 */
public class NextGreaterElement {

    /**
     * 暴力方法
     * 缺点：
     * 1. 时间复杂度有点高；
     * 2. 有重复计算，只需要遍历一遍就可以知道当前的下一个大的值是什么
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElement(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[i]) {
                    nums[i] = nums[j];
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                nums[i] = -1;
            }
        }

        return nums;
    }

    /**
     * 采用递增栈的方式
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElement2(int[] nums) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            // 入栈和出栈
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                // 出栈，选出较大的入栈，小的都出去
                stack.pop();
            }
            // 录入结果
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 先找到 nums1 的在 nums2 中的下标
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j]) {
                    nums1[i] = j;
                }
            }
        }

        // 查找 nums2 的所有的下一个较大元素
        Stack<Integer> stack = new Stack<>();
        int[] res2 = new int[len2];
        for (int i = len2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            res2[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }

        int[] res = new int[len1];
        // 根据下标获取结果
        for (int i = 0; i < len1; i++) {
            res[i] = res2[nums1[i]];
        }

        return res;
    }

    public int[] getNextGreaterElement(int[] nums) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[len];

        for (int i = len - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        // 求 num2 中的下一个元素
        int[] stack = getNextGreaterElement(nums2);
        // 将 num2 和 下一个元素做一个映射
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(nums2[i], stack[i]);
        }
        // 在映射中获取 num1 的元素
        for (int i = 0; i < nums1.length; i++) {
            res[i] = hashMap.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        int[] r = nextGreaterElement.getNextGreaterElement(nums);
        int[] res = nextGreaterElement.nextGreaterElement2(nums);

        for (int i : r) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for (int re : res) {
            System.out.print(re + "\t");
        }
        System.out.println();
        nextGreaterElement.nextGreaterElement(nums);
        for (int re : nums) {
            System.out.print(re + "\t");
        }
        System.out.println();

        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
        int[] element2 = nextGreaterElement.nextGreaterElement2(nums1, nums2);

        for (int i : element2) {
            System.out.print(i + "\t");
        }
        System.out.println();

        int[] element = nextGreaterElement.nextGreaterElement(nums1, nums2);

        for (int i : element) {
            System.out.print(i + "\t");
        }

    }

}

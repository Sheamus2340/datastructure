package org.sheamus.datastructure.array.leetcode;

import java.util.*;

public class NextGreaterElements {
    /**
     * 理解错误题目意思
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        // 数组排序，去重
        int[] newNums = nums.clone();
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int[] res = new int[newNums.length];

        for (int i = 0; i < newNums.length; i++) {
            Integer index = map.get(newNums[i]);
            if (index == newNums.length - 1) {
                res[i] = -1;
            } else {
                if (i != 0 && res[i - 1] > nums[index + 1]) {
                    res[i] = res[i - 1];
                } else {
                    res[i] = nums[index + 1];
                }
            }
        }

        return res;
    }

    /**
     * 单调栈
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        Stack<Integer> stack = new Stack<>();
        // 5, 4, 3, 2, 1
        // ---------------------
        // 0: stack = 0  : ret =
        // 1: stack = 0,1 : ret =
        // 2: stack = 0,1,2
        // 3: stack = 0,1,2,3
        // 4: stack = 0,1,2,3,4
        // 5: stack = 0,1,2,3,4,0
        for (int i = 0; i < n * 2 - 1; i++) {

            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                Integer pop = stack.pop();
                ret[pop] = nums[i % n];
            }

            // 放索引，取余得到索引位置
            stack.push(i % n);
        }

        return ret;
    }

    public void print(int[] ints) {
        for (int n : ints) {
            System.out.print(n + "\t\t");
        }
        System.out.println();
    }

    /**
     * 暴力方法
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements3(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int[] newNums = fillDouble(nums, 2);

        Arrays.fill(res, -1);
        for (int i = 0; i < len; i++) {
            res[i] = findNext(newNums, i);
        }
        return res;
    }

    public int[] fillDouble(int[] nums, int n) {
        int len = nums.length;
        int[] res = new int[n * len];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < len; i++) {
                res[(j * len) + i] = nums[i];
            }
        }
        return res;
    }

    // 1, 2, 3, 4, 3, 1, 2, 3, 4, 3
    public int findNext(int[] nums, int index) {
        int len = nums.length / 2;
        int start = index;
        int end = start + len;
        int num = nums[index];
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > num) {
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        // int[] nums = {1, 2, 3, 4, 3};
        NextGreaterElements nextGreaterElements = new NextGreaterElements();
        int[] ints = nextGreaterElements.nextGreaterElements(nums);
        nextGreaterElements.print(ints);
        int[] nums2 = {5, 4, 3, 2, 1};
        int[] ints2 = nextGreaterElements.nextGreaterElements2(nums2);
        nextGreaterElements.print(ints2);
        int[] nums3 = {5, 4, 3, 2, 1};
        int[] ints3 = nextGreaterElements.nextGreaterElements3(nums3);
        nextGreaterElements.print(ints3);
    }
}

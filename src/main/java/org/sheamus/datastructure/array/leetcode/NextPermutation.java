package org.sheamus.datastructure.array.leetcode;

import java.util.Arrays;

/**
 * 下一个排列
 * 排列后的按照 字典排序 的下一个序列是什么
 * https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 */
public class NextPermutation {

    /**
     * 1 2 3 4 6 5
     * 1 2 3 5 6 4
     * 1 2 3 5 4 6
     * ---
     * 1 2 3 4 6 5
     * 1 2 3 4 5 6
     * 1 2 3 5 4 6
     *
     * 整体思想：
     * 1. 先找到需要交换位置的索引；
     * 2. 排序；
     * 3. 交换位置；
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        for (int i = len - 1; i > 0; i--) {
            // 找到下一个交换数字的位置 i
            if (nums[i] > nums[i - 1]) {
                // 先升序
                Arrays.sort(nums, i, len);
                if (swap(nums, len, i)) return;
            }
        }
        // 如果找不到就直接是序列的正序
        Arrays.sort(nums);
    }

    /**
     * 找到与 i 交换位置的数
     *
     * @param nums
     * @param len
     * @param i
     * @return
     */
    private boolean swap(int[] nums, int len, int i) {
        // 交换 索引 i - 1 和 需要交换的位置
        for (int j = i; j < len; j++) {
            if (nums[j] > nums[i - 1]) {
                int temp = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = temp;
                return true;
            }
        }
        return false;
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        NextPermutation nextPermutation = new NextPermutation();
        nextPermutation.nextPermutation(nums);
        nextPermutation.print(nums);
    }

}

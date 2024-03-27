package org.sheamus.algorithm.array.leetcode;

/**
 * title：
 */
public class FirstMissingPositive {

    /**
     * 找出第一个缺失的正整数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 1. 遍历数组，将每个元素放到正确的位置上
        for (int i = 0; i < n; i++) {
            // 1.1 如果当前元素小于等于0或者大于数组长度，或者当前元素已经放到正确的位置上，则跳过
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        // 2. 遍历数组，找到第一个没有放到正确的位置上的元素
        for (int i = 0; i < n; i++) {
            // 2.1 如果当前元素不是正确的位置上的元素，则返回当前元素的位置加1
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 3. 如果没有找到，则返回数组长度加1
        return n + 1;
    }

    private void swap(int[] nums, int i, int i1) {
        int temp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = temp;
    }

}

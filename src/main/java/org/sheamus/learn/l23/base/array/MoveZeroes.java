package org.sheamus.learn.l23.base.array;

/**
 * 移动零
 * <a href="https://leetcode.cn/problems/move-zeroes/">...</a>
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        moveNum(nums, 0);
    }

    /**
     * 移动任意数字到最后并且保证数字的有序性
     *
     * @param nums
     * @param num
     */
    public void moveNum(int[] nums, int num) {
        int index = 0;
        int len = nums.length;
        for (int n : nums) {
            if (n != num) {
                nums[index++] = n;
            }
        }
        while (index < len) {
            nums[index++] = num;
        }
    }

}

package org.sheamus.learn.l23.base.array;

/**
 * 两个数之和 - 有序数组
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">...</a>
 */
public class TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        int[] res = new int[2];

        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

}

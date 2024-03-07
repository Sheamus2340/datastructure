package org.sheamus.algorithm.array.leetcode;

/**
 * title：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int l = 0, r = 0;
        while (r < len) {
            int indexVal = nums[l];
            int curVal = nums[r];

            if (indexVal == curVal) {
                nums[l++] = curVal;
            }

            r++;
        }
        return l + 1;

    }
}

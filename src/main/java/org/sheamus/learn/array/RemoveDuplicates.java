package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {

    /**
     * double pointer
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int l = 0, r = 0;
        int len = nums.length;

        while (r < len) {
            int indexValue = nums[l];
            int curValue = nums[r];
            if (indexValue != curValue) {
                nums[++l] = nums[r];
            }
            r++;
        }
        return l + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int removed = removeDuplicates.removeDuplicates(nums);
        System.out.println(removed);
    }

}

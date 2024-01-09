package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/remove-element/
 */
public class RemoveElement {

    /**
     * 1. double pointer
     * 2. sliding window
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // 3,2,2,3  3
        // 0,1,2,2,3,0,4,2   2

        int l = 0, r = 0;
        int len = nums.length;

        while (r < len) {
            int curValue = nums[r];

            if (curValue == val) {
                r++;
            } else {
                if (l != r) {
                    nums[l] = curValue;
                }
                l++;
                r++;
            }

        }
        return l;


    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int target = 3;
        RemoveElement removeElement = new RemoveElement();
        int removed = removeElement.removeElement(nums, target);
        System.out.println(removed);
    }

}

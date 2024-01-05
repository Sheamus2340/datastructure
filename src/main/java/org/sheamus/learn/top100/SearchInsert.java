package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/search-insert-position/?envType=study-plan-v2&envId=top-100-liked
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                right = mid - 1;
            } else {
                res = mid;
                break;
            }
        }

        return res == -1 ? left + 1 : res;

    }
}

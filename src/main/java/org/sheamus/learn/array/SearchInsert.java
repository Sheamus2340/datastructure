package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/search-insert-position/
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        //简单的二分查找
        if (len == 0) {
            return 0;
        }
        //小知识点： java数组的最大长度为int的最大值
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //此时left = right
        return target <= nums[left] ? left : left + 1;
    }

}

package org.sheamus.learn.l23.base.array;

/**
 * 二分查找
 * <a href="https://leetcode.cn/problems/binary-search/">...</a>
 */
public class Search {

    public static int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }

        if (left == nums.length) return -1;

        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        System.out.println(search(nums, target));
    }

}

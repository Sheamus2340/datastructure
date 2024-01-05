package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/next-permutation/?envType=study-plan-v2&envId=top-100-liked
 * https://leetcode.cn/problems/next-permutation/solutions/792950/xia-yi-ge-pai-lie-zi-dian-xu-by-kaiwenyu-vu47/?envType=study-plan-v2&envId=top-100-liked
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    //翻转从start开始的数组序列，原来肯定是降序（从大到小），转换为升序（从小到大）
    public void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}

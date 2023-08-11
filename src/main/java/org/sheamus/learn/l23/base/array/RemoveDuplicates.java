package org.sheamus.learn.l23.base.array;

/**
 * 删除有序数组中的重复项
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">...</a>
 */
public class RemoveDuplicates {

    /**
     * 思路：
     * 使用双指针的方法解决
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            // 分两种情况
            // 1. 相等的时候，q 后移
            // 2. 不相等的时候，p+1 赋值为q的值，p 后移
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int i = removeDuplicates.removeDuplicates(arr);
        System.out.println(i);

    }
}

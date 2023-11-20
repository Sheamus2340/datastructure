package org.sheamus.learn.l23.base.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 最长连续子序列
 * 要求：时间复杂度为 O(n)
 */
public class LongestConsecutive {

    /**
     * 不能使用排序
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveUnUseful(int[] nums) {
        // 穷举的时间复杂度是 O(n^2)
        Arrays.sort(nums); // 破坏了规则
        int max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) { // 时间复杂度破坏了规则
            int maxSize = 0;
            int num = nums[i];
            for (int j = i + 1; j < len; j++) {
                ++num;
                if (num != nums[j]) {
                    break;
                }
                maxSize++;
            }
            if (maxSize > max) {
                max = maxSize;
            }
        }

        return max + 1;
    }

    /**
     * 使用 Set 存储代替一个循环
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int i = 0; i < len; i++) {

        }


        return max;
    }


    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive.longestConsecutive(nums));
    }
}

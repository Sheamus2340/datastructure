package org.sheamus.learn.top100;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked
 */
public class LongestConsecutive {

    /**
     * 0,3,7,2,5,8,4,6,0,1
     * 9
     * 解法一：排序 + 遍历
     * 解法二：HashMap
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return len;
        }
        Arrays.sort(nums);

        int res = 0;

        for (int i = 1; i < len; i++) {
            int sum = 0;

            while (nums[i - 1] + 1 == nums[i]) {
                i++;
                sum++;
            }
            res = Math.max(sum, res);
        }

        return res + 1;
    }

    public int longestConsecutive2(int[] nums) {
        Set<Integer> dic = new HashSet<>();
        for (int num : nums) {
            dic.add(num);
        }
        int res = 0;
        for (int num : nums) {
            int sum = num + 1;
            int len = 0;
            while (dic.contains(sum)) {
                sum++;
                len++;
            }
            res = Math.max(len, res);
        }
        return res + 1;
    }

    public int longestConsecutive3(int[] nums) {
        // key: 元素 ， value: 这个元素的最长序列
        Map<Integer, Integer> dic = new HashMap<>();
        int len = nums.length;
        int res = 0;

        for (int i = 0; i < len; i++) {
            if (!dic.containsKey(nums[i])) {

                Integer left = dic.getOrDefault(nums[i] - 1, 0);
                Integer right = dic.getOrDefault(nums[i] + 1, 0);

                int now = left + right + 1;

                dic.put(nums[i], now);
                dic.put(nums[i] - left, now);
                dic.put(nums[i] + right, now);
                res = Math.max(res, now);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        //int consecutive = longestConsecutive.longestConsecutive(nums);
        int consecutive2 = longestConsecutive.longestConsecutive3(nums);

        //System.out.println(consecutive);
        System.out.println(consecutive2);
    }

}

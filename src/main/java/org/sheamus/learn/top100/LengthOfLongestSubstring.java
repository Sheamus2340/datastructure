package org.sheamus.learn.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked
 */
public class LengthOfLongestSubstring {

    /**
     * 典型的滑动窗口
     * abcabcbb
     * i = 0, left = 0, right = 1, abcabcbb  窗口中添加值
     * i = 1, left = 0, right = 2, abcabcbb  窗口中添加值
     * i = 2, left = 0, right = 3, abcabcbb  窗口中添加次数
     * i = 3, left = 1, right = 3, abcabcbb  窗口中减去次数
     *
     * [left, right)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = 0;
        // 存储窗口存在的元素，key 是元素， value 是元素出现的次数
        Map<Character, Integer> windows = new HashMap<>();
        int res = 0;
        int len = arr.length;

        while (right < len) {
            char c = arr[right];
            // 进入窗口
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            // 窗口向右侧滑动
            right++;
            // 这个地方的索引还是窗口的右开区间值
            while (windows.get(c) > 1) {
                // 元素从窗口剔除
                char d = arr[left];
                windows.put(d, windows.getOrDefault(d, 0) - 1);
                // 窗口向右侧收缩
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring(str));
    }

}

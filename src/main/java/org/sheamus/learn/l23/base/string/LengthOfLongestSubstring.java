package org.sheamus.learn.l23.base.string;

import java.util.HashMap;

/**
 * 最长不重复子串
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">...</a>
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        char[] sArr = s.toCharArray();
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int maxLen = 0;

        while (right < sArr.length) {
            Character c = sArr[right];
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                Character d = sArr[left];
                left++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }

            // 记录最长串
            maxLen = Math.max(right - left, maxLen);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

}

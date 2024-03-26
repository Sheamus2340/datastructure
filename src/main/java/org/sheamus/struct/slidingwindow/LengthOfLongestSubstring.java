package org.sheamus.struct.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * title：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> dic = new HashMap<>();

        int start = 0, end = 0;
        int maxLen = 0;
        int len = chars.length;

        while (end < len) {

            // 不包含
            dic.put(chars[end], dic.getOrDefault(chars[end], 0) + 1);
            // 包含
            while (dic.get(chars[end]) > 1) {
                dic.put(chars[start], dic.getOrDefault(chars[start], 0) - 1);
                start++;
            }
            end++;

            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

}

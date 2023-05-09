package org.sheamus.algorithm.string.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    /**
     * 滑动窗口
     * a b c a b c b b
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int left = 0;
        int max = 0;

        // 存放字符以及对应的索引值
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            if (map.containsKey(arr[i])) {
                left = Math.max(left, map.get(arr[i]) + 1);
            }
            map.put(arr[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("bbbbb"));
    }
}

package org.sheamus.algorithm.string.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
 */
public class MinWindow {

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int sLen = sChars.length;
        int tLen = tChars.length;
        int left = 0;
        int right = 0;

        for (int i = 0; i < sLen; i++) {
            if (map.containsKey(sChars[i]) && list.contains(tChars[i])) {
                left = Math.max(left, sChars[i] + 1);
            }
            map.put(sChars[i], i);
            right = Math.min(right, i - left + 1);
        }
        return s.substring(left, right);
    }

    public String minWindow2(String s, String t) {

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        System.out.println(minWindow.minWindow2("ADOBECODEBANC", "ABC"));
    }

}

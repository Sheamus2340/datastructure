package org.sheamus.algorithm.string.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * title：https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindAnagrams {

    /**
     * Input: s = "cbaebabacd", p = "abc"
     * Output: [0,6]
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int tLen = s.length();
        List<Integer> res = new ArrayList<>();

        // 初始化 need
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        // 表示 need 的一个元素已经都满足了
        int valid = 0;

        // 记录最小覆盖子串的起始索引及长度
        int len = p.length();

        while (right < tLen) {
            char c = s.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c)) {
                // 元素的个数和窗口的数据相等时才进行增加1
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left == len) {
                    res.add(left);
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
        return res;

    }

    public void print(List<Integer> anagrams) {
        if (anagrams != null) {
            for (Integer anagram : anagrams) {
                System.out.print(anagram + "\t");
            }
        }
    }

    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();
        List<Integer> anagrams = findAnagrams.findAnagrams("cbaebabacd", "abc");
        findAnagrams.print(anagrams);
    }
}

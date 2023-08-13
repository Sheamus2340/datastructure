package org.sheamus.learn.l23.base.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 异位词
 * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">...</a>
 */
public class FindAnagrams {

    /**
     * 滑动窗口
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        // 初始化
        for (char c : pArr) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;
        List<Integer> res = new ArrayList<>();

        while (right < sArr.length) {

            char c = sArr[right];
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    count++;
                }
            }

            while (right - left >= pArr.length) {

                if (count == need.size()) {
                    res.add(left);
                }

                char d = sArr[left];
                left++;

                if (need.containsKey(d)) {
                    if (window.getOrDefault(d, 0).equals(need.get(d))) {
                        count--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }

            }

        }

        return res;
    }


    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = findAnagrams(s, p);
        for (Integer anagram : anagrams) {
            System.out.println(anagram);
        }
    }
}

package org.sheamus.learn.l23.base.string;

import java.util.HashMap;

/**
 * 字符串的排列
 * <a href="https://leetcode.cn/problems/permutation-in-string/">...</a>
 */
public class CheckInclusion {

    /**
     * s2 是否 包含 s1 的排列
     * ab
     * eidbaooo
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        // 初始化 need
        for (Character c : s1Arr) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while (right < s2Arr.length) {
            Character c = s2Arr[right];
            right++;

            // 扩大窗口
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    count++;
                }
            }

            // 缩小窗口
            while (right - left >= s1.length()) {

                // 存储最小值
                if (count == need.size()) {
                    return true;
                }

                Character d = s2Arr[left];
                left++;

                if (need.containsKey(d)) {
                    if (window.getOrDefault(d, 0).equals(need.get(d))) {
                        count--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }

}

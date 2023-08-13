package org.sheamus.learn.l23.base.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 *
 * <a href="https://leetcode.cn/problems/minimum-window-substring/description/">...</a>
 */
public class MinWindow {

    /**
     * 思路一：
     * 穷举所有的可能。这个可能性太多，穷举完成不了。
     * <p>
     * 思路二：
     * 双指针的方法
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        char[] targetArr = t.toCharArray();
        char[] sourceArr = s.toCharArray();

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 初始化 need
        for (Character c : targetArr) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 窗口左右边界 [left, right)
        int left = 0, right = 0;
        // 表示窗口中满足 need 条件的字符数，用于收缩窗口
        int valid = 0;

        // 最小串的起始位置和长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < sourceArr.length) {
            Character c = sourceArr[right];
            right++;
            // 进行窗口数据的更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 窗口中元素的个数与需要的个数相等
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否需要收缩
            while (valid == need.size()) {
                // 这里更新最小覆盖串，先记录下最小子串的位置，后面需要尝试缩减窗口
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移除窗口的字符
                Character d = sourceArr[left];
                left++;
                // 进行窗口内数据的更新
                if (need.containsKey(d)) {
                    // 减少需要的个数
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    // 维护窗口的元素的个数
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }

            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static String minWindowForAI(String s, String t) {
        // 将字符串t转换为字符数组，并存储每个字符的出现次数
        char[] targetArr = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : targetArr) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 初始化左指针和右指针，以及变量count、start和maxLength
        int left = 0, right = 0;
        int count = 0;
        int start = 0;
        int maxLength = 0;
        // 创建一个StringBuilder用于存储结果字符串
        StringBuilder result = new StringBuilder();

        // 当右指针未到达字符串s的末尾时，不断移动右指针
        while (right < s.length()) {
            char c = s.charAt(right);
            // 如果右指针所指的字符在哈希映射need中存在，则根据该字符在哈希映射need中的出现次数更新变量count
            if (need.containsKey(c)) {
                if (count == need.get(c)) {
                    count++;
                } else {
                    count--;
                }
            }
            right++;

            // 如果窗口中所有字符的出现次数都满足条件，则找到满足条件的窗口
            if (count == need.size()) {
                // 通过移动左指针缩小窗口，直到窗口不再满足条件
                while (count == need.size()) {
                    char leftChar = s.charAt(left);
                    if (need.containsKey(leftChar)) {
                        if (count == need.get(leftChar)) {
                            count--;
                        } else {
                            count++;
                        }
                    }
                    left++;
                }
                // 更新最小窗口的最大长度和结果字符串的起始位置
                maxLength = Math.max(maxLength, right - left);
                result.append(s.substring(start, right));
                start = right;
            }
        }
        // 如果未找到满足条件的窗口，则返回空字符串；否则返回结果字符串
        if (maxLength == 0) {
            return "";
        } else {
            return result.toString();
        }
    }

    /**
     * 求最小覆盖串
     *
     * @param source
     * @param target
     * @return
     */
    public static String getMinString(String source, String target) {
        int NEED_DEFAULT_VALUE = 0;
        char[] targetArr = target.toCharArray();
        char[] sourceArr = source.toCharArray();

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        // 初始化 need 的数值
        for (Character t : targetArr) {
            need.put(t, need.getOrDefault(t, NEED_DEFAULT_VALUE) + 1);
        }
        // 左闭右开的窗口
        int left = 0, right = 0;
        // 用来管理已经遍历过字符中出现 need 的字符的个数
        int count = 0;
        // 记录最小串结果的位置
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < source.length()) {

            Character cur = sourceArr[right];
            right++;

            // 增加窗口
            if (need.containsKey(cur)) {
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                if (window.get(cur).equals(need.get(cur))) {
                    count++;
                }
            }

            // 缩小窗口
            while (count == need.size()) {

                // 先记录最小串的位置
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                Character d = sourceArr[left];
                left++;

                if (need.containsKey(d)) {
                    if (window.getOrDefault(d, 0).equals(need.get(d))) {
                        count--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }

        }

        return minLen == Integer.MAX_VALUE ? "" : source.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        String res = getMinString("ADOBECODBANC", "ABC");
        System.out.println(res);
    }


}

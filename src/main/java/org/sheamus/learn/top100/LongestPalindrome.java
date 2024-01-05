package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/?envType=study-plan-v2&envId=top-100-liked
 * https://leetcode.cn/problems/longest-palindromic-substring/solutions/63641/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/?envType=study-plan-v2&envId=top-100-liked
 */
public class LongestPalindrome {

    /**
     * 输入：s = "babad"
     * 输出："bab"
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        // 中心扩散法
        char[] chars = s.toCharArray();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < chars.length; i++) {
            int res = 0;
            while (i > 0 && (chars[i - 1] == chars[i + 1] || chars[i] == chars[i + 1] || chars[i - 1] == chars[i])) {

            }

        }
        return "";

    }

}

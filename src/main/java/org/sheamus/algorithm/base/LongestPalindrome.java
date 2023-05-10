package org.sheamus.algorithm.base;

/**
 * 最长回文子串
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        // 找出以 a 为中心最长回文，记录索引下标
        char[] arr = s.toCharArray();
        int low = 0, high = 0;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            int iL = i - 1;
            int iH = i + 1;
            int sum = 0;
            while (iL >= 0 && iH < arr.length) {

                if (arr[iL] == arr[iH]) {
                    sum++;
                    iL--;
                    iH++;
                } else {
                    break;
                }

            }
            if (sum > max) {
                max = sum;
                low = iL + 1;
                high = iH - 1;
            }
        }
        return s.substring(low, high + 1);
    }

    public static String longestPalindrome2(String s) {
        // 找出以 a 为中心最长回文，记录索引下标
        char[] arr = s.toCharArray();
        int low = 0, high = 0;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            int iL = i - 1;
            int iH = i;
            // 记录回文子串的长度
            int sum = 0;
            while (iL >= 0 && iH < arr.length) {

                if (arr[iL] == arr[iH]) {
                    sum++;
                    iL--;
                    iH++;
                } else {
                    break;
                }

            }
            // 判断是否大于原来最大的
            if (sum > max) {
                max = sum;
                low = iL + 1;
                high = iH - 1;
            }
        }
        return s.substring(low, high + 1);
    }

    /**
     * 动态规划的解法：
     * 1. 动态规划的定义：i，j之间如果是最长回文子串 p(i, j) = true
     * 2. 方程：p(i, j), p(i + 1, j - 1) = true && p(i) == p(j)
     */
    public static String longestPalindrome3(String str) {
        // 表示回文字符串的最大长度
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        // 字符串的总长度
        int len = str.length();
        // 标识当前的i到j的字符串是否是回文字符串
        boolean[][] dp = new boolean[len][len];
        // 字符串数组
        char[] strArr = str.toCharArray();

        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (strArr[l] == strArr[r] && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l > max) {
                        leftMax = l;
                        rightMax = r;
                        max = r - l;
                    }
                }
            }
        }
        return str.substring(leftMax, rightMax + 1);
    }


    public static void main(String[] args) {
        String s = longestPalindrome2("1211112");
        System.out.println(s);
    }


}

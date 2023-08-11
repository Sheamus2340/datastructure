package org.sheamus.learn.l23.base.string;

/**
 * 最长回文子串
 * <a href="https://leetcode.cn/problems/longest-palindromic-substring/">...</a>
 */
public class LongestPalindrome {

    /**
     * 中心化扩散
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int maxLen1 = 0;
        int maxLeft1 = 0, maxRight1 = 0;
        int maxLen2 = 0;
        int maxLeft2 = 0, maxRight2 = 0;
        StringBuffer stringBuffer = new StringBuffer();
        int len = arr.length - 1;

        // 分奇偶
        for (int i = 1; i < len; i++) {
            int l = i - 1, r = i + 1;
            while (l >= 0 && r <= len && arr[l] == arr[r]) {
                l--;
                r++;
            }

            if (maxLen1 < (i - l) * 2) {
                maxLen1 = (i - l) * 2;
                maxLeft1 = l;
                maxRight1 = r;
            }

        }
        Result result = new Result(maxLen1, maxLeft1, maxRight1);

        for (int i = 0; i < len; i++) {
            int l = i, r = i + 1;
            while (l >= 0 && r <= len && arr[l] == arr[r]) {
                l--;
                r++;
            }

            if (maxLen2 < (i - l) * 2) {
                maxLen2 = (i - l) * 2;
                maxLeft2 = l;
                maxRight2 = r;
            }
        }

        if (result.maxLen1 > maxLen2) {
            for (int i = result.maxLeft1 + 1; i < result.maxRight1; i++) {
                stringBuffer.append(arr[i]);
            }
        } else {
            for (int i = maxLeft2 + 1; i < maxRight2; i++) {
                stringBuffer.append(arr[i]);
            }
        }

        if (stringBuffer.length() == 0) {
            return arr[0] + "";
        }

        return stringBuffer.toString();
    }

    private static class Result {
        public final int maxLen1;
        public final int maxLeft1;
        public final int maxRight1;

        public Result(int maxLen1, int maxLeft1, int maxRight1) {
            this.maxLen1 = maxLen1;
            this.maxLeft1 = maxLeft1;
            this.maxRight1 = maxRight1;
        }
    }

    public static void main(String[] args) {
        String s = "babad";
        String s1 = longestPalindrome(s);
        System.out.println(s1);
    }
}

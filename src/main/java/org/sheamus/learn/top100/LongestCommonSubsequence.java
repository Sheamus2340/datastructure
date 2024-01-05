package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/?envType=study-plan-v2&envId=top-100-liked
 * https://leetcode.cn/problems/longest-common-subsequence/solutions/963588/zui-chang-gong-gong-zi-xu-lie-tu-jie-dpz-6mvz/?envType=study-plan-v2&envId=top-100-liked
 */
public class LongestCommonSubsequence {
    /**
     * 定义 f[i][j]表示字符串text1的[1,i]区间和字符串text2的[1,j]区间的最长公共子序列长度（下标从1开始）
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[n][m];
    }

}

package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/unique-paths/?envType=study-plan-v2&envId=top-100-liked
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        if (m == 0 && n == 0) {
            return 0;
        }
        dp[0][0] = 0;
        dp[1][0] = 1;
        dp[0][1] = 1;
        dp[1][1] = 2;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private int res(int m, int n) {
        // 分解为子问题
        // 边界问题
        if (n == 0 && m >= 0) {
            return 1;
        }
        if (m == 0 && n >= 0) {
            return 1;
        }

        return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
    }
}

package org.sheamus.datastructure.array.leetcode;

import java.util.Arrays;

public class MinPathSum {
    // 动态规划
    // dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j]
    // dp[0][0] = grid[0][0]
    public int minPathSum(int[][] grid) {
        return dp(grid, grid.length - 1, grid[0].length - 1);
    }

    public int dp(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        int leftMin = dp(grid, i - 1, j);
        int rightMin = dp(grid, i, j - 1);

        return Math.min(leftMin, rightMin) + grid[i][j];
    }

    class Solution {

        int[][] memo;

        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // 构造备忘录，初始值全部设为 -1
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);

            return dp(grid, m - 1, n - 1);
        }

        int dp(int[][] grid, int i, int j) {
            // base case
            if (i == 0 && j == 0) {
                return grid[0][0];
            }
            if (i < 0 || j < 0) {
                return Integer.MAX_VALUE;
            }
            // 避免重复计算
            if (memo[i][j] != -1) {
                return memo[i][j];
            }
            // 将计算结果记入备忘录
            memo[i][j] = Math.min(
                    dp(grid, i - 1, j),
                    dp(grid, i, j - 1)
            ) + grid[i][j];

            return memo[i][j];
        }
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        MinPathSum minPathSum = new MinPathSum();
        System.out.println(minPathSum.minPathSum(nums));
    }
}

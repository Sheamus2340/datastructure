package org.sheamus.algorithm.dp;

public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        // dp[i] 表示与i结尾的最长严格子序列的长度；
        // dp[i+1] = dp[i] , dp[i] + 1 判断 nums[i+1] > nums[i]
        // dp[0] = 1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = 1;
                continue;
            }
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = dp[i-1];
            }
        }

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        lengthOfLIS.lengthOfLIS(new int[] {4,10,4,3,8,9});
    }
}

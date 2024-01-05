package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/house-robber/?envType=study-plan-v2&envId=top-100-liked
 * https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/?envType=study-plan-v2&envId=top-100-liked
 */
public class Rob {
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i-1] ,dp[i-2] + nums[i - 1]);
        }

        return dp[len];
    }

}

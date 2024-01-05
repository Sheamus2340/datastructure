package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/climbing-stairs/?envType=study-plan-v2&envId=top-100-liked
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        if(n <= 2){//当有0、1、2个台阶时，分别有0、1、2种方法
            return n;
        }
        int[] dp = new int[n]; //记录从1个台阶到n个台阶中，每个台阶所需要的方法。
        dp[0] = 1; //dp[0]是第一个台阶
        dp[1] = 2; //dp[1]是第二个台阶
        for(int i = 2; i < n; i++){//i=2时是第三个台阶，i=n-1时是第n个台阶。
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1]; //dp[n-1]是第n个台阶
    }


    /**
     * 设跳上 n 级台阶有 f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1 级或 2 级台阶。
     *
     * 当为 1 级台阶： 剩 n−1 个台阶，此情况共有 f(n−1) 种跳法。
     * 当为 2 级台阶： 剩 n−2 个台阶，此情况共有 f(n−2) 种跳法。
     *
     * 这是一个典型的DP问题
     * dp[0] = 1, dp[1] = 2
     * dp[i] = dp[i - 1] + dp[i - 2]
     *
     * @param n
     * @return
     */
    public int climbStairsWays(int n) {
        int[] dp = new int[n];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 自顶向下
     *
     * @param n
     * @return
     */
    public int climbWays(int n) {
        int[] memo = new int[n + 1];

        return climb(memo, n);
    }

    private int climb(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }
        // 备忘录中包含结果
        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = climb(memo, n - 1) + climb(memo, n - 2);

        return memo[n];
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbWays(40));
    }

}

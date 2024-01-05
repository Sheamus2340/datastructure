package org.sheamus.learn.top100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/coin-change/?envType=study-plan-v2&envId=top-100-liked
 * https://leetcode.cn/problems/coin-change/solutions/137661/javadi-gui-ji-yi-hua-sou-suo-dong-tai-gui-hua-by-s/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {

    int min = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        findWay(coins, amount, min);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void findWay(int[] coins, int amount, int min) {
        if (amount == 0) {
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            findWay(coins, amount - coins[i], min);

        }

    }

    /**
     * 1、确定 base case，这个很简单，显然⽬标⾦额 amount 为 0 时算法返回 0，因为不需要任何硬币就已经凑出⽬标⾦额了。
     * 2、确定「状态」，也就是原问题和⼦问题中会变化的变量。由于硬币数量⽆限，硬币的⾯额也是题⽬给定的，只有⽬标⾦额会不断地向 base case 靠近，
     * 所以唯⼀的「状态」就是⽬标⾦额 amount。
     * 3、确定「选择」，也就是导致「状态」产⽣变化的⾏为。⽬标⾦额为什么变化呢，因为你在选择硬币，你每选择⼀枚硬币，就相当于减少了⽬标⾦额。所以说所有硬币的⾯值，就是你的「选择」。
     * 4、明确 dp 函数/数组的定义。我们这⾥讲的是⾃顶向下的解法，所以会有⼀个递归的 dp 函数，⼀般来说函数的参数就是状态转移中会变化的量，也就是上⾯说到的「状态」；函数的返回值就是题⽬要求我们计算的量。
     * 就本题来说，状态只有⼀个，即「⽬标⾦额」，题⽬要求我们计算凑出⽬标⾦额所需的最少硬币数量。所以我们可以这样定义 dp 函数：dp(n) 表示，输⼊⼀个⽬标⾦额 n，返回凑出⽬标⾦额 n 所需的最少硬币数量。
     */
    /**
     * 自顶向下
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeMinWays(int[] coins, int amount) {
        if (amount == 0) return amount;
        int[] memo = new int[amount + 1];
        // 备忘录初始化为⼀个不会被取到的特殊值，代表还未被计算
        Arrays.fill(memo, -666);
        return dp(coins, memo, amount);
    }

    private int dp(int[] coins, int[] memo, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        // 查备忘录，防⽌重复计算
        if (memo[amount] != -666) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = dp(coins, memo, amount - coins[i]);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    // 自底向上
    public int coinsChangeWay(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 数组⼤⼩为 amount + 1，初始值也为 amount + 1
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最⼩值
            for (int coin : coins) {
                // ⼦问题⽆解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
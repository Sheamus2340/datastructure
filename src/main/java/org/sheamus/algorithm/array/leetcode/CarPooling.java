package org.sheamus.algorithm.array.leetcode;

/**
 * title：https://leetcode.cn/problems/car-pooling/
 */
public class CarPooling {

    public boolean carpooling(int[][] trips, int capacity) {
        // 最多有 1001 个⻋站
        int[] nums = new int[1001];
        // 构造差分解法
        Difference df = new Difference(nums);
        for (int[] trip : trips) {
            // 乘客数量
            int val = trip[0];
            // 第 trip[1] 站乘客上⻋
            int i = trip[1];
            // 第 trip[2] 站乘客已经下⻋
            // 即乘客在⻋上的区间是[trip[1], trip[2] - 1]
            int j = trip[2] - 1;
            // 进⾏区间操作
            df.increment(i, j, val);
        }

        int[] res = df.result();
        // 客⻋⾃始⾄终都不应该超载
        for (int re : res) {
            if (capacity < re) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

}

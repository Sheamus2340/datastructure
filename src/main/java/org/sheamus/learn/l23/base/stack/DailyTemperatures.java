package org.sheamus.learn.l23.base.stack;

import java.util.Stack;

/**
 * 每日温度
 * <a href="https://leetcode.cn/problems/daily-temperatures/">...</a>
 */
public class DailyTemperatures {
    /**
     *
     * { 73, 74, 75, 71, 69, 72, 76, 73 }
     * { 1, 1, 4, 2, 1, 1, 0, 0 }
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();

        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] res = dailyTemperatures.dailyTemperatures(temperatures);

        for (int re : res) {
            System.out.print(re + "\t");
        }

    }

}

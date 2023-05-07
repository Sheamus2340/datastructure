package org.sheamus.datastructure.stack.leetcode;

import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {

    /**
     * 超时
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        // 两个指针
        for (int i = 0; i < len; i++) {
            int curTemperatures = temperatures[i];
            for (int j = i + 1; j < len; j++) {
                if (curTemperatures < temperatures[j]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 递减栈解决这个问题：https://leetcode.cn/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
     * 这个题解的视频是真牛
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        // 73, 74, 75, 71, 69, 72, 76, 73
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }

        return res;
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] res = dailyTemperatures.dailyTemperatures(arr);
        dailyTemperatures.print(res);

        int[] arr2 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res2 = dailyTemperatures.dailyTemperatures2(arr2);
        dailyTemperatures.print(res2);
    }

}

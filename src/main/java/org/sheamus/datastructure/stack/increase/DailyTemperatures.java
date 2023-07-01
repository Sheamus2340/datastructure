package org.sheamus.datastructure.stack.increase;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {

    /**
     * 基本逻辑：
     * 论点：求比当前元素大的下一个元素的差的绝对值
     * 论据：利用一个递增栈来存储一个递增的序列减少递增序列中重复的扫描；
     * 论证：
     * <p>
     *  1. 当栈为空时，直接将当前元素添加入栈；
     *  2. 当栈非空时：
     *      a. 获取当前元素和栈顶元素比较：
     *          1. 当前元素 > 栈顶元素时：
     *              a. 说明找到了第一个大的元素的值，那么就求他们的绝对值；
     *              b. 接下里将栈顶元素出栈，并且继续比较栈顶元素和当前元素的大小（因为栈就是用来保存递增序列的），直到小于；
     *          2. 当前元素 <= 栈顶元素时：
     *              a. 直接入栈;
     *  📢 最后栈中还包含的元素是没有找到后面大的元素的集合，这个地方不需要处理，直接在结果集中默认为0就可以了。
     *</p>
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        // 保存索引值
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                result[stack.peek()] = i - stack.peek();
                stack.removeFirst();
            }
            stack.addFirst(i);
        }
        return result;
    }

    public void print(int[] result) {
        for (int re : result) {
            System.out.print(re + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures.dailyTemperatures(temperatures);
        dailyTemperatures.print(result);
    }

}

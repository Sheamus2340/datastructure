package org.sheamus.algorithm.base;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Calculate {

    /**
     * 执行字符串的结果
     *
     * @param s
     * @return
     */
    public static int calculate(String s) {
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();

        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);

        // 将所有的空格去掉
        s = s.replaceAll(" ", "");

        int len = s.length();
        char[] cs = s.toCharArray();

        for (int i = 0; i < len; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    // 查看字符
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < len && isNum(cs[j])) u = u * 10 + (int) (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
                    ops.addLast(c);
                }
            }
        }

        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }

    /**
     * 使用两个栈实现
     *
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        // 存放所有的数字
        Stack<Integer> nums = new Stack<>();
        // 存放所有的操作，包括 +/-
        Stack<Character> ops = new Stack<>();

        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);

        // 将所有的空格去掉
        s = s.replaceAll(" ", "");

        int len = s.length();
        char[] cs = s.toCharArray();

        for (int i = 0; i < len; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    // 查看字符
                    char op = ops.peek();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < len && isNum(cs[j])) u = u * 10 + (int) (cs[j++] - '0');
                    nums.push(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.push(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peek() != '(') calc(nums, ops);
                    ops.push(c);
                }
            }
        }

        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peek();
    }

    /**
     * 加减运算
     *
     * @param nums
     * @param ops
     */
    static void calc(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        nums.push(op == '+' ? a + b : a - b);
    }

    /**
     * 加减运算
     *
     * @param nums
     * @param ops
     */
    static void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
    }

    /**
     * 判断是否为数字
     *
     * @param c
     * @return
     */
    static boolean isNum(char c) {
        return Character.isDigit(c);
    }

    public static void main(String[] args) {
        System.out.println(calculate("(1+(2-1))+3-(2+1)"));
        System.out.println(calculate2("(1+(2-1))+3-(2+1)"));
    }
}

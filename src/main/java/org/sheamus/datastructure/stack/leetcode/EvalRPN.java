package org.sheamus.datastructure.stack.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 示例 1：
 * <p>
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        // 使用栈
        List<String> flags = Arrays.asList(new String[]{"+", "-", "*", "/"});
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (flags.contains(token)) {
                String first = stack.pop();
                String second = stack.pop();
                String res = calc(first, second, token);
                stack.push(res);
            } else {
                stack.push(token);
            }
        }
        String result = stack.pop();
        return Integer.valueOf(result);
    }

    private String calc(String second, String first, String token) {
        int res = 0;
        switch (token) {
            case "+":
                res = Integer.valueOf(first) + Integer.valueOf(second);
                break;
            case "-":
                res = Integer.valueOf(first) - Integer.valueOf(second);
                break;
            case "*":
                res = Integer.valueOf(first) * Integer.valueOf(second);
                break;
            case "/":
                res = Integer.valueOf(first) / Integer.valueOf(second);
                break;
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String[] tokens = {"4", "13", "5", "/", "+"};
        EvalRPN evalRPN = new EvalRPN();
        System.out.println(evalRPN.evalRPN(tokens));
    }
}

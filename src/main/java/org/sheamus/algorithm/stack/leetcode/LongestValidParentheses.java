package org.sheamus.algorithm.stack.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        int len = charArr.length;
        int[] arr = new int[len];
        Arrays.fill(arr, -2);
        int index = 0;

        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty() && charArr[i] == ')') {
                Character pop = stack.pop();
                if (pop == '(') {
                    arr[index++] = i - 1;
                    arr[index++] = i;
                }
            } else {
                stack.push(charArr[i]);
            }
        }

        Arrays.sort(arr);
        // 最长递增子序列


        return 0;
    }

    public int longestValidParentheses2(String s) {
        //如果为空
        if (s == null || s.length() == 0) return 0;
        //建立一个栈
        Stack<Integer> stack = new Stack<>();
        //这一步可以防止当第一个Character是')'的时候发生越界异常
        stack.push(-1);
        //System.out.println(stack);
        //可以看到stack是[-1]
        int res = 0;
        //遍历栈找寻合适的左右括号
        for (int i = 0; i < s.length(); i++) {
            //如果找到左括号则入栈，为寻找对应右括号做铺垫
            if (s.charAt(i) == '(') stack.push(i);
            else {
                //如果是右括号则出栈
                stack.pop();
                //但是如果栈是空的话还是得（单身的）把右括号放进来
                if (stack.isEmpty()) stack.push(i);
                else {
                    //当前全部人数减去剩余无法配对的人数（单身）即res
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses2("()(()"));
    }
}

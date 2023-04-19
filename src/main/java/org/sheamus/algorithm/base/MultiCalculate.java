package org.sheamus.algorithm.base;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 加减乘除计算器
 */
public class MultiCalculate {

    static Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    public static int calculate(String s) {
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        int n = s.length();
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0);
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
        return nums.peekLast();
    }

    static void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        } else if (op == '^') {
            ans = (int) Math.pow(a, b);
        } else if (op == '%') {
            ans = a % b;
        }
        nums.addLast(ans);
    }

    static boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    public static void main(String[] args) {
        System.out.println(calculate("(1*2)+9/3+1-3*3"));
    }

}

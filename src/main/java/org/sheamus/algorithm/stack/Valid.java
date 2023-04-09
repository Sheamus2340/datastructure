package org.sheamus.algorithm.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Valid {
    public static boolean isValid(String s) {
        // 栈
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if(pairs.containsKey(arr[i])) {
                if(stack.isEmpty() || stack.peek() != pairs.get(arr[i])) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(arr[i]);
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("{()}"));
    }
}

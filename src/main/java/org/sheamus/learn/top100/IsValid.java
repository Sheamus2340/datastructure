package org.sheamus.learn.top100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValid {
    public boolean isValid(String s) {

        Map<Character, Character> dic = new HashMap<>();
        dic.put(')', '(');
        dic.put('}', '{');
        dic.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (dic.containsKey(c)) {
                if (!stack.isEmpty() && stack.peek() == dic.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }

        }

        return stack.isEmpty();

    }

    public static void main(String[] args) {
        String s = "()";
        IsValid isValid = new IsValid();
        boolean valid = isValid.isValid(s);
        System.out.println(valid);

    }
}

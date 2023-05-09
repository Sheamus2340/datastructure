package org.sheamus.algorithm.string.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValid {
    Map<Character, Character> map = new HashMap<>();

    {
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
    }

    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        char[] chars = str.toCharArray();

        for (char c : chars) {
            if (map.containsKey(c) && !stack.isEmpty()) {
                Character pop = stack.pop();
                Character value = map.get(c);
                if (value.equals(pop)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public boolean isValid2(String s) {
        if (s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.empty() || c != stack.pop())
                return false;
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        String str = "({})[]";
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid(str));
        System.out.println(isValid.isValid2(str));
    }
}

package org.sheamus.algorithm.base;

import java.util.HashSet;
import java.util.Set;

public class TwoNumSum {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.trim().equals("")) {
            return 0;
        }
        int max = 1;
        Set<Character> result = new HashSet<Character>();
        result.add(s.charAt(0));
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (result.contains(s.charAt(j))) {
                    break;
                }
                max = Math.max(j - i + 1, max);
                result.add(s.charAt(j));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int reslu = lengthOfLongestSubstring("abcabcbb");
        System.out.println(reslu);
        String s = "121";
        char[] chars = s.toCharArray();
    }
}

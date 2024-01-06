package org.sheamus.learn.array;

import java.util.*;

/**
 * title：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * <p>
 * 这是一个多集合的回溯问题：
 * 在每个集合中选择一下值进行排列组合
 */
public class LetterCombinations {

    // 字典
    static Map<Character, List<Character>> dic = new HashMap<>();

    static {
        dic.put('1', null);
        dic.put('2', Arrays.asList('a', 'b', 'c'));
        dic.put('3', Arrays.asList('d', 'e', 'f'));
        dic.put('4', Arrays.asList('g', 'h', 'i'));
        dic.put('5', Arrays.asList('j', 'k', 'l'));
        dic.put('6', Arrays.asList('m', 'n', 'o'));
        dic.put('7', Arrays.asList('p', 'q', 'r', 's'));
        dic.put('8', Arrays.asList('t', 'u', 'v'));
        dic.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList();
        }
        char[] arr = digits.toCharArray();
        List<List<Character>> arrList = new ArrayList<>();
        for (char c : arr) {
            arrList.add(dic.get(c));
        }

        int len = arr.length;
        List<String> res = new ArrayList<>();
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        dfs(arrList, buffer, index, len, res);

        return res;
    }

    private void dfs(List<List<Character>> arrList, StringBuffer buffer, int index, int len, List<String> res) {
        if (index == len) {
            res.add(buffer.toString());
        } else {
            List<Character> list = arrList.get(index);
            for (Character character : list) {
                buffer.append(character.toString());
                dfs(arrList, buffer, index + 1, len, res);
                buffer.deleteCharAt(index);
            }
        }
    }

    static class Solution {
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        List<String> res = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();

        public List<String> letterCombinations(String digits) {
            //解决快速寻找数组中所有存在目标字母的方法: Map加回溯
            if (digits.isEmpty()) return res;
            backtrack(digits, 0);
            return res;
        }

        public void backtrack(String digits, int index) {
            if (index == digits.length()) res.add(buffer.toString());
            else {
                // 按照索引获取数字
                char digit = digits.charAt(index);
                // 拿到对应的字母
                String letters = phoneMap.get(digit);
                // 选择字母
                for (int i = 0; i < letters.length(); i++) {
                    buffer.append(letters.charAt(i));
                    // 增加索引，选择下一个集合的字母
                    backtrack(digits, index + 1);
                    buffer.deleteCharAt(index);
                }
            }
        }
    }

    private void print(List<String> str) {
        for (String letterCombination : str) {
            System.out.print(letterCombination + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        LetterCombinations.Solution solution = new Solution();
        letterCombinations.print(solution.letterCombinations("23"));

        letterCombinations.print(letterCombinations.letterCombinations("23"));

    }

}

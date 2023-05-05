package org.sheamus.algorithm.string;

import java.util.HashMap;

public class RomanToInt {
    static HashMap<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    /**
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char[] charArray = s.trim().toCharArray();

        int result = 0;

        char preStr = 0;
        char curStr = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (i != 0) {
                preStr = charArray[i - 1];
            }
            curStr = charArray[i];

            if ((curStr == 'V' || curStr == 'X') && preStr == 'I') {
                result += map.get(curStr) - 2 * map.get(preStr);
            } else if ((curStr == 'L' || curStr == 'C') && preStr == 'X') {
                result += map.get(curStr) - 2 * map.get(preStr);
            } else if ((curStr == 'D' || curStr == 'M') && preStr == 'C') {
                result += map.get(curStr) - 2 * map.get(preStr);
            } else {
                result += map.get(curStr);
            }

        }

        return result;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = map.get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = map.get(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }


    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        System.out.println(romanToInt.romanToInt("MCMXCIV"));
        System.out.println(romanToInt.romanToInt2("MCMXCIV"));
    }
}

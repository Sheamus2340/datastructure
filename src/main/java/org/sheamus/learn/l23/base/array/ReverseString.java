package org.sheamus.learn.l23.base.array;

/**
 * 反转字符串
 * <a href="https://leetcode.cn/problems/reverse-string/">...</a>
 */
public class ReverseString {

    public void reverseString(char[] s) {
        int len = s.length;
        int l = 0, r = len - 1;

        while (l < r) {
            char tmp = s[r];
            s[r] = s[l];
            s[l] = tmp;
            r--;
            l++;
        }

    }

}

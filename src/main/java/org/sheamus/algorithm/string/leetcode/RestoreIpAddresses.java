package org.sheamus.algorithm.string.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 回溯其实就是横向遍历，纵向递归的组合
 */
public class RestoreIpAddresses {


    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len > 12 || len < 4) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        dfs(s, len, 0, 4, path, res);
        return res;
    }

    // 需要一个变量记录剩余多少段还没被分割

    private void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {

        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }

            if (residue * 3 < len - i) {
                continue;
            }

            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);

                dfs(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    /**
     * 判断ip的规则
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean judgeIpSegment(String s, int left, int right) {

        int len = right - left + 1;

        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;

        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }

    private void print(List<String> ips) {
        for (String ip : ips) {
            System.out.println(ip);
        }
    }

    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        List<String> ips = restoreIpAddresses.restoreIpAddresses("25525511135");
        restoreIpAddresses.print(ips);
    }

}

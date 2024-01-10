package org.sheamus.learn.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * title：https://leetcode.cn/problems/valid-sudoku/
 *
 */
public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row  = new HashMap<>(), col = new HashMap<>(), area = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            area.put(i, new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '0';
                // 小方块编号和行列的关系为： idx = i / 3 * 3 + j / 3
                int idx = i / 3 * 3 + j / 3;
                if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) return false;
                row.get(i).add(u);
                col.get(j).add(u);
                area.get(idx).add(u);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int i = 3, j = 2;
        int idx = i / 3 * 3 + j / 3;

        int u = '9' - '0';

        System.out.println(u);

    }

}

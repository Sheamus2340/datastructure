package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * title：https://leetcode.cn/problems/n-queens/
 */
public class SolveNQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard, res);
        return res;
    }


    public void backTrack(int n, int row, char[][] chessboard, List<List<String>> res) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0; col < n; ++col) {
            if (isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row + 1, chessboard, res);
                chessboard[row][col] = '.';
            }
        }

    }


    public List<String> Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }


    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i = 0; i < row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }


}

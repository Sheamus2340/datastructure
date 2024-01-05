package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/number-of-islands/?envType=study-plan-v2&envId=top-100-liked
 *
 * 题解
 * https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/?envType=study-plan-v2&envId=top-100-liked
 */
public class NumIslands {

    public int numIslands(int[][] grid) {
        // i,j,k,l
        // 上、下、左、右
       // int i = 0, j = 0;
        // grid[i][j]
        // grid[i - 1][j] grid[i][j + 1] grid[i + 1][j] grid[i][j - 1]

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, i, j);
                }

            }
        }
        return 0;

    }

    int dfs(int[][] grid, int r, int c) {
        // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
        if (!inArea(grid, r, c)) {
            return 1;
        }
        // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
        if (grid[r][c] == 0) {
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

}

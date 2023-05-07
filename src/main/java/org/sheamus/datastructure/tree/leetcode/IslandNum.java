package org.sheamus.datastructure.tree.leetcode;

/**
 * 岛屿数量问题
 * <p>
 * 详细解析参见：
 * https://labuladong.github.io/article/?qno=200
 *
 * 模版：
 * void dfs(int[][] grid, int r, int c) {
 *     // 判断 base case
 *     if (!inArea(grid, r, c)) {
 *         return;
 *     }
 *     // 如果这个格子不是岛屿，直接返回
 *     if (grid[r][c] != 1) {
 *         return;
 *     }
 *     grid[r][c] = 2; // 将格子标记为「已遍历过」
 *
 *     // 访问上、下、左、右四个相邻结点
 *     dfs(grid, r - 1, c);
 *     dfs(grid, r + 1, c);
 *     dfs(grid, r, c - 1);
 *     dfs(grid, r, c + 1);
 * }
 *
 * // 判断坐标 (r, c) 是否在网格中
 * boolean inArea(int[][] grid, int r, int c) {
 *     return 0 <= r && r < grid.length
 *         	&& 0 <= c && c < grid[0].length;
 * }
 *
 * 作者：nettee
 * 链接：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IslandNum {
    // 主函数，计算岛屿数量
    public int numIslands(char[][] grid) {

        int res = 0;
        int m = grid.length, n = grid[0].length;

        // 遍历 grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // 每发现一个岛屿，岛屿数量加一
                    res++;
                    // 然后使用 DFS 将岛屿淹了
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    /**
     *  从 (i, j) 开始，将与之相邻的陆地都变成海水
      */
    void dfs(char[][] grid, int i, int j) {
        // 递归的出口
        if (!inArea(grid, i, j)) {
            // 超出索引边界
            return;
        }

        // 如果这个格子不是岛屿，直接返回
        if (grid[i][j] == '0') {
            // 已经是海水了
            return;
        }

        // 将 (i, j) 变成海水
        grid[i][j] = '0';

        // 淹没上下左右的陆地
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    boolean inArea(char[][] grid, int r, int c) {
      return 0 <= r && r < grid.length
              && 0 <= c && c < grid[0].length;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0'},
                {'0', '1', '0'},
                {'0', '0', '0'},
                {'0', '1', '0'}
        };
        IslandNum islandNum = new IslandNum();
        System.out.println(islandNum.numIslands(grid));
    }
}

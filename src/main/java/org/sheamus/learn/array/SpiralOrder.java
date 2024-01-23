package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * title：https://leetcode.cn/problems/spiral-matrix/
 * <p>
 * 作者：Krahets
 * 链接：https://leetcode.cn/problems/spiral-matrix/solutions/2362055/54-luo-xuan-ju-zhen-mo-ni-qing-xi-tu-jie-juvi/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SpiralOrder {

    /**
     * recursive + used array
     * 边界 就转换方向
     * 终止条件 左右上面都是 used
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<Integer>();
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        Integer[] res = new Integer[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top
            if (++l > r) break;
        }
        return Arrays.asList(res);
    }

}

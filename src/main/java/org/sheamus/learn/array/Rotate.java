package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/rotate-image/
 */
public class Rotate {
    /**
     * 暂存tmp=matrix[i][j]
     * matrix[i][j]←matrix[n−1−j][i]←matrix[n−1−i][n−1−j]←matrix[j][n−1−i]←tmp
     * <p>
     * <p>
     * 作者：Krahets
     * 链接：https://leetcode.cn/problems/rotate-image/solutions/1228078/48-xuan-zhuan-tu-xiang-fu-zhu-ju-zhen-yu-jobi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

}

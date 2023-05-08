package org.sheamus.datastructure.array.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (interval1, interval2) -> {
            return interval1[0] - interval2[0];
        });

        // 铺平
        int len = intervals.length;
        // 标识最近需要处理索引
        int lastIndex = 0;
        int[][] res = new int[len][2];
        res[lastIndex][0] = intervals[0][0];
        res[lastIndex][1] = intervals[0][1];

        for (int i = 1; i < len; i++) {
            int[] tmp = intervals[i];
            if (isGap(res[lastIndex], tmp)) {
                // 需要合并
                int[] re = merge(res[lastIndex], tmp);
                res[lastIndex][0] = re[0];
                res[lastIndex][1] = re[1];
            } else {
                // 不需要
                lastIndex++;
                res[lastIndex][0] = tmp[0];
                res[lastIndex][1] = tmp[1];
            }
        }

        return copyArr(res, lastIndex + 1);
    }

    private int[][] copyArr(int[][] res, int lastIndex) {
        int[][] lastRes = new int[lastIndex][2];
        for (int i = 0; i < lastIndex; i++) {
            lastRes[i][0] = res[i][0];
            lastRes[i][1] = res[i][1];
        }
        return lastRes;
    }

    /**
     * i x j y true
     * i x y j true
     * x i j y true
     * x i y j true
     *
     * @param first
     * @param second
     * @return
     */
    private int[] merge(int[] first, int[] second) {
        int x = first[0], y = first[1];
        int i = second[0], j = second[1];

        if (i <= x && j <= y) {
            return new int[]{i, y};
        }

        if (i <= x && y <= j) {
            return new int[]{i, j};
        }

        if (x <= i && j <= y) {
            return new int[]{x, y};
        }

        if (x <= i && y <= j) {
            return new int[]{x, j};
        }

        return null;
    }

    /**
     * i j
     * x y
     * i j x y false
     * i x j y true
     * i x y j true
     * x i j y true
     * x i y j true
     * x y i j false
     *
     * @param re
     * @param tmp
     * @return
     */
    private boolean isGap(int[] re, int[] tmp) {
        int x = re[0], y = re[1];
        int i = tmp[0], j = tmp[1];
        if (j < x || y < i) {
            return false;
        }
        return true;
    }

    private void print(int[][] res) {
        for (int[] num : res) {
            System.out.println(num[0] + "\t" + num[1]);
        }
    }

    /**
     * 对起点和终点分别进行排序，将起点和终点一一对应形成一个数组。
     * 如果没有overlap,返回当前起点和终点
     * 如果有overlap,判断以下条件
     * <p>
     * 找出最小的起点
     * 如果当前终点大于下一个数组的起点的时候，比较当前终点和下一个终点的大小，取为right
     * 返回满足要求的区间[[left,right]]
     *
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Merge merge = new Merge();
        int[][] res = merge.merge(intervals);
        merge.print(res);

        int[][] intervals2 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res2 = merge.merge2(intervals2);
        merge.print(res2);
    }
}

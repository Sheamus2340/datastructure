package org.sheamus.algorithm.array.leetcode;

/**
 * title：差分数组模版
 */
public class Difference {

    private int[] diff;

    public Difference(int[] nums) {

        if (nums.length == 0) {
            return;
        }

        diff = new int[nums.length];

        diff[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

    }

    /**
     * 闭区间的加上 val 的值
     * <p>
     * 构造差分数组 diff，就可以快速进⾏区间增减的操作，
     * 如果你想对区间 nums[i..j] 的元素全部加3，那么只需要让 diff[i] += 3，
     * 然后再让 diff[j+1] -= 3 即可
     *
     * @param i
     * @param j
     * @param val
     */
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    /**
     * 返回结果数组
     *
     * @return
     */
    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

}

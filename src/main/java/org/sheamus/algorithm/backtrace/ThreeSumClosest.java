package org.sheamus.algorithm.backtrace;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 16. 最接近的三数之和
 *
 * @link https://leetcode.cn/problems/3sum-closest/
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        // target
        // nums[0]                           nums[1]                         nums[2]                          nums[3] nums[4]
        // nums[1] nums[2] nums[3] nums[4]   nums[0] nums[2] nums[3] nums[4] nums[0] nums[1] nums[3] nums[4] ....
        // nums[2] nums[3] nums[4]           nums[2] nums[3] nums[4]

        // 回溯 + 剪枝
        Arrays.sort(nums);



        return 0;
    }




}

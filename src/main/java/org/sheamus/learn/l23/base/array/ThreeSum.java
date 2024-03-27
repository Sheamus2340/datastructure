package org.sheamus.learn.l23.base.array;

import java.util.Arrays;

/**
 * title：
 */

public class ThreeSum {

    /**
     * 计算数组中和为目标值的三元组的数量
     *
     * @param nums   整型数组，要求不为null且长度大于等于3
     * @param target 目标和
     * @return 满足条件的三元组数量
     */
    private static int countTriplets(int[] nums, int target) {
        // 参数校验
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("输入数组 nums 不能为空且长度至少为 3");
        }

        // 对数组进行排序
        Arrays.sort(nums);

        int count = 0;
        int left = 0; // 左指针
        int right = nums.length - 1; // 右指针

        // 固定一个数，使用双指针找到剩余两个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复元素以避免重复计数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (left < i && right > i) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // 找到一个符合条件的三元组
                    count++;

                    // 跳过重复元素以避免重复计数
                    while (left < i && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > i && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }
        }

        return count;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] nums = {1, -2, 3, 0, -1, 2, -3};
        int target = 0;
        System.out.println(countTriplets(nums, target)); // 输出: 4
    }
}




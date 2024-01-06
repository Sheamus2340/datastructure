package org.sheamus.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * title：https://leetcode.cn/problems/4sum/
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> arr = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // nums[i] > target 直接返回, 剪枝操作
            if (nums[i] > 0 && nums[i] > target) return arr;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target) l++;
                    else if (sum > target) r--;
                    else {
                        arr.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        r--;
                        l++;
                    }
                }
            }
        }
        return arr;
    }

}

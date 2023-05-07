package org.sheamus.datastructure.array.leetcode;

import java.util.Arrays;

public class FindKthLargest {

    public static int findKthLargest(int[] nums, int k) {
        Integer[] temp = new Integer[nums.length];
        int index = 0;
        for (int num : nums) {
            temp[index++] = num;
        }
        Arrays.sort(temp, (i, j) -> (j - i));

        return temp[k - 1];
    }

    public static int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length - k];
    }



    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{1,2,3,4,5}, 2));
        System.out.println(findKthLargest2(new int[]{1,2,3,4,5}, 2));
    }
}

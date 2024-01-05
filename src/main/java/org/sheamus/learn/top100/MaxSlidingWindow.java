package org.sheamus.learn.top100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxSlidingWindow {

    /**
     * nums = [1,3,-1,-3,5,3,6,7], k = 3
     * [3,3,5,5,6,7]
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];

        int left = 0, right = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> {
            return o2.compareTo(o1);
        });

        while (right < len) {
            priorityQueue.offer(nums[right]);
            right++;
            if (right - left == k) {
                res[left] = priorityQueue.peek();
                priorityQueue.remove(nums[left]);
                left++;
            }
        }

        return res;
    }

    private int[] toIntArr(List<Integer> resList) {
        int len = resList.size();
        int[] res = new int[len];

        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        int[] ints = maxSlidingWindow.maxSlidingWindow(nums, k);
        maxSlidingWindow.print(ints);
    }

}

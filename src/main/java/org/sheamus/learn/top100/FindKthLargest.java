package org.sheamus.learn.top100;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            int value = queue.peek();
            if (nums[i] > value) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        FindKthLargest findKthLargest = new FindKthLargest();
        System.out.println(findKthLargest.findKthLargest(nums, 2));
    }
}

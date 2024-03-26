package org.sheamus.algorithm.array.leetcode;

/**
 * title：https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 */
public class MoveZeroes {

    /**
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // 表示非零的指针
        int cur = 0;
        // 填充的位置
        int index = 0;
        int len = nums.length;

        while (cur < len) {
            if (nums[index] != 0) {
                index++;
            } else if (nums[cur] != 0 && cur != index) {
                swap(cur, index, nums);
                nums[cur] = 0;
                index++;
            }
            cur++;
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public void moveZeroes2(int[] nums) {
        moveNum(nums, 0);
    }

    public void moveNum(int[] nums, int num) {
        int index = 0;
        int len = nums.length;
        for (int n : nums) {
            if (n != num) {
                nums[index++] = n;
            }
        }
        while (index < len) {
            nums[index++] = num;
        }
    }

    public void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
        moveZeroes.print(nums);

        moveZeroes.moveZeroes2(nums);
    }

}

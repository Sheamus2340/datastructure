package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 */
public class MoveZeroes {

    /**
     * 0,1,0,3,12
     * 1、0，1，0，3，12 index = 0
     * 2、1，1，0，3，12 index = 1
     * 3、1，3，0，3，12 index = 1
     * 4、1，1，3，3，12 index =
     * 5、1，1，3，3，12 index = 5
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        moveNum(nums, 0);
    }

    /**
     * 0,1,0,3,12
     * 1. i = 0 == 0, 0,1,0,3,12  , index = 0
     * 2. i = 1 == 1, 1,1,0,3,12  , index = 1
     * 3. i = 2 == 0, 1,1,0,3,12  , index = 1
     * 4. i = 3 == 3, 1,3,0,3,12  , index = 2
     * 5. i = 4 == 12, 1,3,12,3,12 , index = 3
     *
     * @param nums
     * @param num
     */
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
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveNum(nums, 0);
        moveZeroes.print(nums);
    }

}

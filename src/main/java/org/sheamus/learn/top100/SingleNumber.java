package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/single-number/?envType=study-plan-v2&envId=top-100-liked
 */
public class SingleNumber {

    /**
     * 异或
     *
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     *
     * 任何数于0异或为任何数 0 ^ n => n
     *
     * 相同的数异或为0: n ^ n => 0
     *
     * var a = [2,3,2,4,4]
     *
     * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};

        SingleNumber singleNumber = new SingleNumber();
        int number = singleNumber.singleNumber(nums);
        System.out.println(number);

    }

}

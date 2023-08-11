package org.sheamus.learn.l23.base.array;

/**
 * 移除元素
 * <a href="https://leetcode.cn/problems/remove-element/">...</a>
 *
 * 这道题有点难理解
 * <a href="https://leetcode.cn/problems/remove-element/solutions/575555/shua-chuan-lc-shuang-bai-shuang-zhi-zhen-mzt8/">...</a>
 * <a href="https://leetcode.cn/problems/remove-element/solutions/10388/hua-jie-suan-fa-27-yi-chu-yuan-su-by-guanpengchn/">...</a>
 *
 */
public class RemoveElement {

    /**
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        RemoveElement removeElement = new RemoveElement();
        int i = removeElement.removeElement(nums, 2);
        System.out.println(i);
    }

}

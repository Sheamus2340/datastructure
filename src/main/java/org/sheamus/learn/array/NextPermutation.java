package org.sheamus.learn.array;

import java.util.Arrays;

/**
 * title：https://leetcode.cn/problems/next-permutation/
 * <p>
 * 1. 我们希望下一个数 比当前数大，这样才满足 “下一个排列” 的定义。因此只需要 将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。
 * 比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
 * 2. 我们还希望下一个数 增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
 * a. 在 尽可能靠右的低位 进行交换，需要 从后向前 查找
 * b. 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
 * c. 将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，
 * 交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
 * <p>
 * 作者：Imageslr
 * 链接：https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NextPermutation {

    // 1,2,3,4,5,6
    // 1,2,3,4,6,5
    // 1,2,3,5,4,6
    // 1,2,3,5,6,4
    // 1,2,3,6,4,5
    // 1,2,3,6,5,4
    // 1. 找到两个相邻的数字满足 后面的大于前面；
    // 2. 交换比当前位置最近大的
    // 3. 后面的数字进行自然排序

    /**
     * double pointer
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        boolean flag = false;
        int c = len - 1;

        while (c > 0) {
            if (nums[c] > nums[c - 1]) {
                // c - 1 是需要交换大值的位置
                // 寻找比 nums[c - 1] 大的值
                int cur = nums[c - 1];
                int bigNum = nums[c];
                int index = c;
                for (int i = c; i < len; i++) {
                    if (nums[i] <= bigNum && nums[i] > cur) {
                        bigNum = nums[i];
                        index = i;
                    }
                }
                // 交换 位置
                int value = nums[c - 1];
                nums[c - 1] = nums[index];
                nums[index] = value;
                // 重新排序 c -> len
                Arrays.sort(nums, c, len);
                flag = true;
                break;
            }
            c--;
        }

        if (!flag) {
            Arrays.sort(nums, 0, len);
        }
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1,1,5};

        NextPermutation nextPermutation = new NextPermutation();
        nextPermutation.nextPermutation(nums);
        nextPermutation.print(nums);
    }

}

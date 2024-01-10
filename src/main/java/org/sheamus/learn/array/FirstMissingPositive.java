package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/first-missing-positive/
 */
public class FirstMissingPositive {

    /**
     * 作者：Jackey
     * 链接：https://leetcode.cn/problems/first-missing-positive/solutions/2576045/java-tong-su-yi-dong-san-ci-bian-li-by-j-yp6b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }


}

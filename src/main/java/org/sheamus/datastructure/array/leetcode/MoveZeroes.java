package org.sheamus.datastructure.array.leetcode;

public class MoveZeroes {

    /**
     * 两遍循环
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                int index = findNext(nums, i, len);
                swap(nums, i, index);
            }
        }
    }

    /**
     * 两次循环
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for (int i = j; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 双指针
     *
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j, j 是记录 0 索引的位置
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    /**
     * 查询下一个非零的数
     */
    public int findNext(int[] nums, int start, int len) {
        for (int i = start + 1; i < len; i++) {
            if (nums[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    public void swap(int[] nums, int i, int j) {
        if (j == -1) {
            return;
        }
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
        moveZeroes.print(nums);
        int[] nums1 = new int[]{0, 1, 0, 3, 12};
        moveZeroes.moveZeroes2(nums1);
        moveZeroes.print(nums1);

        int[] nums3 = new int[]{0, 1, 0, 3, 12};
        moveZeroes.moveZeroes3(nums3);
        moveZeroes.print(nums3);
    }
}

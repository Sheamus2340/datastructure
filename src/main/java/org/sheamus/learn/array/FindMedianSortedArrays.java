package org.sheamus.learn.array;

/**
 * title：https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * TODO 需要一定的数学理论支持
 * 将数组划分了2份，不断地淘汰
 */
public class FindMedianSortedArrays {

    /**
     * solution: binary search
     * 1,2
     * 3,4
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int mn = m + n;

        int index = 0;

        while (index <= mn) {


        }
        return 0.0;
    }

    /**
      1,4,7,9,11   7
     2,6,7,8       6.5

     1,2,4,6,7,7,8,9,11


     1,2,3,4,5

     6,7,8,9

     5


     */

    public int getMidNum(int[] nums, int left, int right) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {};
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double medianSortedArrays = findMedianSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

}

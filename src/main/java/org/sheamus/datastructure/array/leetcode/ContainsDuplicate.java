package org.sheamus.datastructure.array.leetcode;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> list = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                return true;
            }
            list.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}

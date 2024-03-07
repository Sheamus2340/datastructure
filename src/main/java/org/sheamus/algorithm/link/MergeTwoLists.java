package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }

        cur.next = list1 != null ? list1 : list2;

        return pre.next;
    }

    public static void main(String[] args) {

    }

}

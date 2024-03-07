package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/partition-list/
 */
public class Partition {

    /**
     * 使用双指针的方式
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallList = new ListNode(-1);
        ListNode smallCur = smallList;
        ListNode bigList = new ListNode(-1);
        ListNode bigCur = bigList;

        while (head != null) {
            if (head.val < x) {
                smallCur.next = head;
                smallCur = smallCur.next;
            } else {
                bigCur.next = head;
                bigCur = bigCur.next;
            }
            head = head.next;
        }

        if (bigList.next != null) {
            smallCur.next = bigList.next;
            bigCur.next = null;
        }

        return smallList.next;
    }

}

package org.sheamus.learn.l23.base.link;

/**
 * 分隔链表
 * <a href="https://leetcode.cn/problems/partition-list/">Leetcode</a>
 */
public class Partition {
    /**
     * 思路：
     * 将一个链表拆分成两个链表，再合并成一个
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1);
        ListNode smallCur = small;
        ListNode big = new ListNode(-1);
        ListNode bigCur = big;

        while (head != null) {
            if (head.val < x) {
                smallCur.next = head;
                smallCur = smallCur.next;
                head = head.next;
            } else {
                bigCur.next = head;
                bigCur = bigCur.next;
                head = head.next;
            }
        }

        if (big.next != null) {
            smallCur.next = big.next;
            bigCur.next = null;
        }

        return small.next;
    }
}

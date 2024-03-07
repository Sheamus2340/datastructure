package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        ListNode pre = new ListNode(-1);
        ListNode cur = pre;

        cur.next = slow;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            cur = cur.next;
        }

        if (slow != null) {
            cur.next = slow.next;
        }

        return pre.next;
    }

}

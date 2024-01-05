package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-100-liked
 */
public class RemoveNthFromEnd {
    /**
     * head = [1,2,3,4,5], n = 2
     * [1,2,3,5]
     * 快慢指针
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinal = new ListNode(-1);

        sentinal.next = head;
        ListNode slow = sentinal;
        ListNode fast = sentinal;
        int i = n;
        while (fast.next != null) {
            i--;
            fast = fast.next;
            if (i < 0) {
                slow = slow.next;
            }
        }

        slow.next = slow.next.next;

        return sentinal.next;
    }

    public ListNode remove(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        while (n != 0) {
            start = start.next;
            n--;
        }
        while (start.next != null) {
            n--;
            start = start.next;
            if (n <= 0) {
                end = end.next;
            }
        }
        end.next = end.next.next;
        return pre.next;
    }
}

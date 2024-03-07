package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/middle-of-the-linked-list/
 */
public class MiddleNode {

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }


}

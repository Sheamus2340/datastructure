package org.sheamus.learn.l23.base.link;

/**
 * 求链表的中间节点
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">...</a>
 */
public class MiddleNode {

    /**
     * 思路：
     * 快指针是慢指针的2倍速度前进
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

}

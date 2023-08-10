package org.sheamus.learn.l23.base.link;

/**
 * 判断链表是否有环
 * <a href="https://leetcode.cn/problems/c32eOV/">...</a>
 */
public class DetectCycle {

    /**
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }

        // 重置快节点
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}

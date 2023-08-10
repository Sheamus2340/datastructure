package org.sheamus.learn.l23.base.link;

/**
 * 链表中倒数第k个节点
 * <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/">...</a>
 */
public class GetKthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        // 双指针: 快慢指针
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {

    }

}

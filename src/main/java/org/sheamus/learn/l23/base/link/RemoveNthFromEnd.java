package org.sheamus.learn.l23.base.link;

/**
 * 删除倒数第N个节点
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">...</a>
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next;
        }

        pre.next = slow.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head5 = new ListNode(5);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2, head3);
        ListNode head = new ListNode(1, head2);

        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode listNode = removeNthFromEnd.removeNthFromEnd(head, 2);
        listNode.print(listNode);
    }

}

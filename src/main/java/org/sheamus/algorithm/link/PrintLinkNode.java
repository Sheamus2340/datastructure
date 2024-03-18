package org.sheamus.algorithm.link;

/**
 * title：打印链表节点
 */
public class PrintLinkNode {

    public void print(ListNode head) {
        ListNode dummy = head;
        while (dummy != null) {
            System.out.print(dummy.val + "\t");
            dummy = dummy.next;
        }
        System.out.println();
    }

}

package org.sheamus.algorithm.link;

/**
 * title：链表的翻转
 */
public class ReverseLink {

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;

    }

    public static void main(String[] args) {
        ListNode ln = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ln.next = ln2;
        ListNode ln3 = new ListNode(3);
        ln2.next = ln3;
        ListNode ln4 = new ListNode(4);
        ln3.next = ln4;
        ListNode ln5 = new ListNode(5);
        ln4.next = ln5;

        ReverseLink reverseLink = new ReverseLink();
        ListNode reverse = reverseLink.reverse(ln);
        PrintLinkNode printLinkNode = new PrintLinkNode();
        printLinkNode.print(reverse);
    }

}

package org.sheamus.algorithm.link;

public class CopyLinked {

    public ListNode copy(ListNode head) {
        ListNode pre = new ListNode(0);
        // cur指向pre节点
        ListNode cur = pre;

        while (head != null) {
            // 构建节点
            cur.next = new ListNode(head.val);
            cur = cur.next;
            head = head.next;
        }
        return pre.next;
    }

    public ListNode copyList(ListNode head) {
        ListNode pre = new ListNode(0, head);
        ListNode cur = pre;
        while (cur != null) {
            cur = cur.next;
        }
        return pre.next;
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
        CopyLinked copyLinked = new CopyLinked();
        copyLinked.copy(ln);
    }

}

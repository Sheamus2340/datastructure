package org.sheamus.algorithm.stack;

public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(0, head);
        ListNode cur = pre;
        int i = 0;
        while (cur != null) {
            cur = cur.next;
            if (i % 2 == 0 && cur != null && cur.next != null) {
                int tmp = cur.next.val;
                cur.next.val = cur.val;
                cur.val = tmp;
            }
            i++;
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
        SwapPairs swapPairs = new SwapPairs();
        swapPairs.swapPairs(ln);
    }

}

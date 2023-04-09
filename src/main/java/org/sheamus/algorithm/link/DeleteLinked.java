package org.sheamus.algorithm.link;

import java.util.Stack;

public class DeleteLinked {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = size(head);
        // 创建一个新链表，回填元素
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        int i = 1;

        while (head != null) {
            if (i == size - n + 1) {
                head = head.next;
                i++;
                continue;
            }
            cur.next = new ListNode(head.val);
            cur = cur.next;
            head = head.next;
            i++;
        }
        return pre.next;
    }

    public int size(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    public static void main(String[] args) {
        ListNode ln = new ListNode();
        ln.val = 1;
        ListNode ln2 = new ListNode();
        ln2.val = 2;
        ln.next = ln2;
        ListNode ln3 = new ListNode();
        ln3.val = 3;
        ln2.next = ln3;
        ListNode ln4 = new ListNode();
        ln4.val = 4;
        ln3.next = ln4;
        ListNode ln5 = new ListNode();
        ln5.val = 5;
        ln4.next = ln5;
        DeleteLinked sd = new DeleteLinked();
        sd.removeNthFromEnd2(ln , 2);
    }

    // 栈
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode cur = pre;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode prev = stack.peek();
        prev.next = prev.next.next;

        return pre.next;
    }
}

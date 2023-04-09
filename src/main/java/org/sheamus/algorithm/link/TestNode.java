package org.sheamus.algorithm.link;

import java.util.Properties;

public class TestNode {
    public static int len(ListNode head) {
        ListNode pre = new ListNode(0, head);
        ListNode cur = pre;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len - 1;
    }

    public static int len2(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    public static void ring(ListNode head) {
        ListNode cur = head;

        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = head;

    }

    public static ListNode swapLinked(ListNode head) {
        // pre + 临时节点
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    public static void main(String[] args) {
//        ListNode ln = new ListNode(1);
//        ListNode ln2 = new ListNode(2);
//        ln.next = ln2;
//        ListNode ln3 = new ListNode(3);
//        ln2.next = ln3;
//        ListNode ln4 = new ListNode(4);
//        ln3.next = ln4;
//
//        System.out.println(len(ln));
//        System.out.println(len2(ln));
//        swapLinked(ln);
        //ring(ln);


                Properties p = System.getProperties();

                p.list(System.out);

                System.out.print("CPU个数:");

                System.out.println(Runtime.getRuntime().availableProcessors());


    }
}

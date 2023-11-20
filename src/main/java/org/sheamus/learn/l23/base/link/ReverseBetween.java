package org.sheamus.learn.l23.base.link;

import java.util.ArrayDeque;

/**
 * 反转链表II
 * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/">...</a>
 */
public class ReverseBetween {

    /**
     * 利用栈
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode cur = head;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode start = new ListNode(), end = new ListNode();
        int index = 0;
        while (cur != null) {
            if (index == left) {
                start = cur;
            }
            if (index == right) {
                end = cur;
            }
            if (index > right) {
                break;
            }
            if (index > left && index < right) {
                stack.push(cur);
            }
            cur = cur.next;
            index++;
        }
        ListNode node = new ListNode(-1);
        ListNode c = node;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            c.next = pop;
            c = c.next;
        }
        c.next = end;
        start.next = node.next;
        return pre.next;
    }

    public static void main(String[] args) {
        reverseBetween(null, 1, 1);
    }

}

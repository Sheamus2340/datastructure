package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class ReserveGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode start = head, end = head;
        // 没有 k 个，直接返回，有就拆分
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head;
            }
            end = end.next;
        }

        ListNode reversed = reverseArrange(start, end);
        // 递归并且连接起来，这个 start 节点很关键
        start.next = reverseKGroup(end, k);
        return reversed;
    }

    public ListNode reverseArrange(ListNode start, ListNode end) {
        ListNode pre = null, cur, next;
        cur = start;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}

package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != curB) {
            if (null == curA) curA = headB;
            else curA = curA.next;

            if (null == curB)
                curB = headA;
            else
                curB = curB.next;

        }

        return curA;
    }

}

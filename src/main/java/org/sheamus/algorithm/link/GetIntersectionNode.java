package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != curB) {
            if (null == curA)
                curA = headB;
            else
                curA = curA.next;
            if (null == curB)
                curB = headA;
            else
                curB = curB.next;
        }

        return curA;
    }

    public static void main(String[] args) {
        // listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
        ListNode headA3 = new ListNode(4, null);
        ListNode headA2 = new ListNode(3, headA3);
        ListNode headA1 = new ListNode(2, headA2);
        ListNode headA = new ListNode(1, headA1);

        ListNode headB3 = new ListNode(4, headA2);
        ListNode headB2 = new ListNode(3, headB3);
        ListNode headB1 = new ListNode(2, headB2);
        ListNode headB = new ListNode(1, headB1);

        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        ListNode intersectionNode = getIntersectionNode.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode.val);
    }

}

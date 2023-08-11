package org.sheamus.learn.l23.base.link;

import java.util.HashSet;

/**
 * 链表相交
 * <a href="https://leetcode.cn/problems/3u1WK4/solutions/1037741/liang-ge-lian-biao-de-di-yi-ge-zhong-he-0msfg/">...</a>
 */
public class GetIntersectionNode {
    /**
     * 使用HashSet的方式来存储一个链表，然后遍历另一个链表来判断是否存在
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();
        while (headA != null) {
            hashSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (hashSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    public ListNode connect(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    public static void main(String[] args) {
        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        getIntersectionNode.getIntersectionNode(null, null);
    }
}

package org.sheamus.algorithm.link;

/**
 * title：https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
public class DeleteDuplicates {

    /**
     * 删除链表中重复的元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            int curVal = cur.val;
            int nextVal = cur.next.val;
            if (nextVal == curVal) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

}

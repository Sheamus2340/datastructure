package org.sheamus.learn.top100;

/**
 * https://leetcode.cn/problems/reverse-linked-list/?envType=study-plan-v2&envId=top-100-liked
 */
public class ReverseList {

    /**
     *  sentinel -> 1 -> 2 -> 3 -> 4 -> 5
     *  sentinel    1 <- 2    3 -> 4 -> 5
     *
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode temp = new ListNode(-1);
        temp.next = head;

        while (head != null) {
            ListNode next = head.next;
            head.next = temp;
            head = next;
        }
        return temp;
    }

}

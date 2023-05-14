package org.sheamus.template.link;

/**
 * 链表遍历模版
 */
public class LinkedListTemplate {

    /**
     * 迭代的方式
     *
     * @param head
     */
    public void traverseWithIterator(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            // TODO 迭代访问 p.val

        }
    }

    /**
     * 递归方式
     *
     * @param head
     */
    public void traverseWithRecursion(ListNode head) {
        // TODO 递归访问 head.val
        traverseWithRecursion(head.next);
    }

}

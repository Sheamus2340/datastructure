package org.sheamus.algorithm.link;

/**
 * title：
 */
public class FindFromEnd {

    /**
     * 倒数第 k 个 元素
     * 快慢指针
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先⾛ k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时⾛ n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }

}

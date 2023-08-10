package org.sheamus.learn.l23.base.link;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @link <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">LeetCode</a>
 */
public class MergeTwoLists {
    /**
     * 非递归模型
     *
     * @param list1 第一个有序链表
     * @param list2 第二个有序链表
     * @return result
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 哨兵模式：记录结果的前一个节点
        ListNode res = new ListNode();
        // 缓存当前节点
        ListNode cur = res;
        while (list1 != null || list2 != null) {
            // 构建新节点
            ListNode node = new ListNode();
            if (list1 == null) {
                node.val = list2.val;
                list2 = list2.next;
            } else if (list2 == null) {
                node.val = list1.val;
                list1 = list1.next;
            } else {
                if (list1.val >= list2.val) {
                    node.val = list2.val;
                    list2 = list2.next;
                } else {
                    node.val = list1.val;
                    list1 = list1.next;
                }
            }
            cur.next = node;
            cur = node;
        }
        return res.next;
    }

    /**
     * 递归模型
     *
     * @param list1 第一个有序链表
     * @param list2 第二个有序链表
     * @return result
     */
    public ListNode recursion(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val > list2.val) {
            // 这个地方是比较难理解的，需要提供对应的实例进行分析
            // 将结果赋值给需要递归的元素
            ListNode result = recursion(list1, list2.next);
            list2.next = result;
            return list2;
        } else {
            ListNode result = recursion(list1.next, list2);
            list1.next = result;
            return list1;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();

        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode result = mergeTwoLists.mergeTwoLists(list1, list2);
        mergeTwoLists.recursion(list1, list2);
    }
}

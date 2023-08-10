package org.sheamus.learn.l23.base.link;

import java.util.PriorityQueue;

/**
 * 合并 K 个有序链表
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">...</a>
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/solutions/220518/4-chong-fang-fa-xiang-jie-bi-xu-miao-dong-by-sweet/">最佳解题</a>
 */
public class MergeKLists {
    /**
     * 思路：
     * 1. 每次遍历 K 个数组元素，求出最小的；
     * 2. 然后组装到新的链表上；
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
            ListNode minNode = null;
            // 记录最小值的位置
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            if (minPointer == -1) {
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode[] lists) {
        // 定义一个最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // 把元素装入最小堆中
        for (ListNode node: lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return res.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        ListNode list11 = new ListNode(4);
        ListNode list12 = new ListNode(5);
//        ListNode list13 = new ListNode(6);

        ListNode list21 = new ListNode(3);
        ListNode list22 = new ListNode(4);
//        ListNode list23 = new ListNode(7);

        ListNode list31 = new ListNode(6);
//        ListNode list32 = new ListNode(7);
//        ListNode list33 = new ListNode(8);

        ListNode list1 = new ListNode(1);
        list1.next = list11;
        list11.next = list12;
//        list12.next = list13;
        ListNode list2 = new ListNode(1);
        list2.next = list21;
        list21.next = list22;
//        list22.next = list23;
        ListNode list3 = new ListNode(2);
        list3.next = list31;
//        list31.next = list32;
//        list32.next = list33;

        lists[0] = list1;
        lists[1] = list2;
        lists[2] = list3;

        MergeKLists mergeKLists = new MergeKLists();
//        ListNode merge = mergeKLists.merge(lists);
//        mergeKLists.print(merge);

        ListNode listNode = mergeKLists.mergeKLists(lists);
        listNode.print(listNode);
    }
}

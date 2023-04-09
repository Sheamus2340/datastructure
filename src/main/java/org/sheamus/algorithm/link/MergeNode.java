package org.sheamus.algorithm.link;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeNode {
    public ListNode mergeKLists(ListNode[] lists) {
        // 分治
        // 堆排序 + 优化堆排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 全部放入堆
        for (int i = 0; i < lists.length; i++) {
            while(lists[i] != null) {
                queue.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }

        // 从堆中取出数据
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        while(!queue.isEmpty()) {
            cur.next = new ListNode(queue.poll().val);
            cur = cur.next;
        }

        return pre.next;
    }

    // 优化 堆的大小
    public ListNode mergeKLists1(ListNode[] lists) {
        // 分治
        // 堆排序 + 优化堆排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // 从堆中取出数据
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        // 堆中每次只放K个元素
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }

        while(!queue.isEmpty()) {
            ListNode poll = queue.poll();
            cur.next = new ListNode(poll.val);
            cur = cur.next;
            if (poll.next != null) {
                queue.add(poll.next);
            }
        }

        return pre.next;
    }

}

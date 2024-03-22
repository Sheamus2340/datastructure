package org.sheamus.concurrency.queue;

/**
 * title：
 */
public class FFQueue {

    ListNode<Integer> head = null;

    public FFQueue() {
    }

    public FFQueue(int data) {
        head = new ListNode<>(data);
    }

    public void offer(int data) {
        ListNode<Integer> dataNode = new ListNode<>(data);

        if (head == null) {
            head = dataNode;
        } else {
            // 插入尾部
            ListNode<Integer> cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = dataNode;
        }
    }

    public ListNode<Integer> poll() {

        if (head == null) {
            return null;
        }

        ListNode<Integer> next = head.next;
        head.next = null;
        ListNode<Integer> cur = head;
        head = next;

        return cur;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void print() {
        ListNode<Integer> cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("---- 输入 ----");
        FFQueue ffQueue = new FFQueue(1);
        ffQueue.offer(5);
        ffQueue.offer(2);
        ffQueue.offer(3);

        ffQueue.print();

        System.out.println("---- 输出 ----");
        while (!ffQueue.isEmpty()) {
            System.out.println(ffQueue.poll().val);
        }
    }

    static class ListNode<T> {
        int val;
        ListNode<T> next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        public ListNode(int val, ListNode<T> next) {
            this.val = val;
            this.next = next;
        }
    }

}



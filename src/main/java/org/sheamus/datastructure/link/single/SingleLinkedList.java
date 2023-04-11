package org.sheamus.datastructure.link.single;

/**
 * 单向链表的基本操作
 * Created by Sheamus on 2018/7/10.
 */
public class SingleLinkedList<T> {
    private Node head;

    public SingleLinkedList() {
    }

    public Node getHead() {
        return head;
    }

    public SingleLinkedList(Node head) {
        this.head = head;
    }

    public void addNode(T value) {
        //初始化要加入的节点
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        //临时节点
        Node temp = head;
        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;
    }

    /**
     * 删除尾结点
     *
     * @return 尾结点数据
     */
    public T removeNode() {
        if (head == null) {
            return null;
        }
        //临时节点
        Node temp = head;
        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        return (T) temp.data;
    }


    public void print() {
        print0(head);
    }

    private void print0(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        node = node.next;
        print0(node);
    }

}

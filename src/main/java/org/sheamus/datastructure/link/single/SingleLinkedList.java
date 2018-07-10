package org.sheamus.datastructure.link.single;

/**
 * 单向链表的基本操作
 * Created by Sheamus on 2018/7/10.
 */
public class SingleLinkedList {
    private Node head;

    public SingleLinkedList() {}

    public SingleLinkedList(Node head) {
        this.head = head;
    }

    public void addNode(String value) {
        //初始化要加入的节点
        Node newNode = new Node(value);
        if(head == null) {
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



    public void print() {
        print0(head);
    }

    private void print0(Node node) {
        if(node == null) {
            return;
        }
        System.out.println(node.data);
        node = node.next;
        print0(node);
    }

}

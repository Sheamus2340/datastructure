package org.sheamus.datastructure.link.single;

/**
 * 单向链表
 * Created by Sheamus on 2018/7/10.
 */
public class Node<T> {
    public T data;
    public Node next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }
}

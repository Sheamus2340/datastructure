package org.sheamus.datastructure.link.doubly;

public class Node<T> {
    T data;
    Node pre;
    Node next;

    public Node() {
    }

    public Node(T t, Node pre, Node next) {
        this.data = t;
        this.pre = pre;
        this.next = next;
    }

}

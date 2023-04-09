package org.sheamus.datastructure.stack.link;

import org.sheamus.datastructure.link.single.Node;
import org.sheamus.datastructure.link.single.SingleLinkedList;

public class Stack<T> {
    private SingleLinkedList linkedList;

    public Stack() {
        linkedList = new SingleLinkedList();
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        linkedList.addNode(newNode);
    }

    public T pop() {
        return (T) linkedList.removeNode();
    }


}

package org.sheamus.datastructure.queue;

import java.util.ArrayDeque;

public class DequeList {

    public boolean isFlag (ArrayDeque<Integer> deque) {
        Integer first = deque.removeFirst();
        Integer last = deque.removeLast();

        if (first.equals(last)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(1);
        DequeList linkedList = new DequeList();
        System.out.println(linkedList.isFlag(deque));
    }

}

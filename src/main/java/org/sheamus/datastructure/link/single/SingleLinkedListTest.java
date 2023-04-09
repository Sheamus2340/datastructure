package org.sheamus.datastructure.link.single;

/**
 * Created by Sheamus on 2018/7/10.
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        printList(A);

        LinkedListReverser reversor = LinkedListReverser.RECURSION;
        System.out.println(reversor.getStrategy() + ":");
        Node tmp = reversor.execute(A);
        print(tmp);
        System.out.println();
        reversor = LinkedListReverser.NO_RECURSION;
        System.out.println(reversor.getStrategy() + ":");
        print(reversor.execute(tmp));

    }


    public static void printList(Node node) {
        if (node == null) {
            System.out.println();
        } else {
            System.out.print(node.data);
            if (node.next != null) {
                System.out.print("->");
            }
            printList(node.next);
        }
    }


    private static void print(Node node) {
        while (true) {
            System.out.print(node.data);
            node = node.next;
            if (node != null) {
                System.out.print("->");
            } else {
                break;
            }
        }
    }
}

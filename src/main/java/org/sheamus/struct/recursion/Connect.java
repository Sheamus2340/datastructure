package org.sheamus.struct.recursion;

/**
 * title：
 */
public class Connect {

    public Node connect(Node root) {
        travese(root.left, root.right);
        return root;
    }

    public void travese(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }

        node1.next = node2;

        travese(node1.left, node1.right);
        travese(node1.right, node2.left);
        travese(node2.left, node2.right);
    }

}

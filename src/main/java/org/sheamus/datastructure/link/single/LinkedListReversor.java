package org.sheamus.datastructure.link.single;

/**
 * Created by Sheamus on 2018/7/10.
 */
public enum LinkedListReversor {
    RECURSION("递归") {
        @Override
        public Node execute(Node node) {
            Node prev = null;
            if (node == null || node.next == null) {
                prev = node;
            } else {
                Node tmp = execute(node.next);
                node.next.next = node;
                node.next = null;
                prev = tmp;
            }
            return prev;
        }
    },
    NO_RECURSION("非递归") {
        @Override
        public Node execute(Node node) {
            Node prev = null;
            while (node != null) {
                Node tmp = node;
                node = node.next;
                tmp.next = prev;
                prev = tmp;
            }
            return prev;
        }
    };
    private String strategy;

    private LinkedListReversor(String strategy) {
        this.strategy = strategy;
    }

    public abstract Node execute(Node node);

    public String getStrategy() {
        return strategy;
    }
}

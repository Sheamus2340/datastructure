package org.sheamus.learn.l23.base.array;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 计算数组中所有的数据的结果
 */
public class EventLoop {

    /**
     * 计算出 config 中的所有结果
     * 费和税的计算结果
     *
     * @param configs
     * @param request
     */
    public List<Node> calculate(List<Node> configs, int request) {
        List<Node> result = new ArrayList<>();

        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        Map<Integer, Node> feeMap = new HashMap<>();

        // 首先将费计算出来，将税放入队列中
        for (Node config : configs) {
            if (config.getType().equals(NodeType.Fee)) {
                // 计算结果
                calcute(config, request);
                feeMap.put(config.getId(), config);
                result.add(config);
            } else if (config.getType().equals(NodeType.Tax)) {
                queue.offer(config);
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.getType().equals(NodeType.Fee)) {
                throw new RuntimeException("类型错误");
            }

            int parentId = node.getParentId();
            Node parentNode = feeMap.get(parentId);

            if (parentNode == null) {
                throw new RuntimeException("配置错误");
            }

            calcute(node, parentNode.getResult());
            result.add(node);
        }

        return result;
    }

    private void calcute(Node node, int request) {
        int result = node.getValue() * request;
        node.setResult(result);
    }


    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();
        List<Node> nodes = eventLoop.createData();

        int base = 5;
        List<Node> calculate = eventLoop.calculate(nodes, base);
        eventLoop.print(calculate);
    }

    private void print(List<Node> nodes) {
        for (Node node : nodes) {
            System.out.println(node.getId() + " 的结果是：" + node.getResult());
        }
    }

    public List<Node> createData() {
        List<Node> nodes = new ArrayList<>();

        Node node1 = new Node(1, 1, 0, 1);
        Node node2 = new Node(2, 2, 1, 2);
        Node node3 = new Node(3, 3, 1, 2);

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);


        return nodes;
    }


}

class Node {

    int id;
    int value;
    int result;
    int parentId;
    NodeType type;

    public Node() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getResult() {
        return result;
    }

    public int getParentId() {
        return parentId;
    }

    public NodeType getType() {
        return type;
    }

    public Node(int id) {
        this.id = id;
    }

    public Node(int id, int value, int parentId, int type) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
        NodeType temp = null;
        for (NodeType nodeType : NodeType.values()) {
            if (nodeType.getType() == type) {
                temp = nodeType;
            }
        }
        this.type = temp;
    }

    public Node(int id, int value, int result, int parentId, int type) {
        this.id = id;
        this.value = value;
        this.result = result;
        this.parentId = parentId;
        NodeType temp = null;
        for (NodeType nodeType : NodeType.values()) {
            if (nodeType.getType() == type) {
                temp = nodeType;
            }
        }
        this.type = temp;
    }
}

enum NodeType {
    Fee(1, "费"), Tax(2, "税");
    private int type;
    private String name;

    NodeType() {
    }

    NodeType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

package org.sheamus.datastructure.tree;

/**
 * 树的节点对象
 * Created by Sheamus on 2018/7/4.
 */
public class Node {
    private int keyData;// 节点的key
    private int otherData;// 节点的其他值
    private Node leftNode;// 左节点
    private Node rightNode;// 右节点

    public Node(int keyData, int otherData) {
        this.keyData = keyData;
        this.otherData = otherData;
    }

    public int getKeyData() {
        return keyData;
    }

    public void setKeyData(int keyData) {
        this.keyData = keyData;
    }

    public int getOtherData() {
        return otherData;
    }

    public void setOtherData(int otherData) {
        this.otherData = otherData;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}

package org.sheamus.datastructure.tree;

/**
 * 二叉树
 * Created by Sheamus on 2018/7/4.
 */
public class Tree {
    private Node root;// 通过跟节点去查找其他所有的的节点

    public void insert(int key,int otherData) {
        if(root == null) {
            root = new Node(key,otherData);
        } else {
            Node current = root;

            while(true) {
                //放入左边的树中去,root.getKeyData() > key,这种写法有点问题,因为root节点是一个特殊的节点，
                //这里本应该是一个当前节点的概念，所以可以用一个变量来表示
                if(current.getKeyData() > key) {
                    if(current.getLeftNode() == null) {
                        Node leftNode = new Node(key,otherData);
                        current.setLeftNode(leftNode);
                        break;
                    } else {
                        current = current.getLeftNode();
                    }
                } else {// 放入右边的树中去
                    if(current.getRightNode() == null) {
                        Node rightNode = new Node(key,otherData);
                        current.setRightNode(rightNode);
                        break;
                    } else {
                        current = current.getRightNode();
                    }
                }
            }
        }
    }


    public void insert2(int key,int otherData) {
        Node newNode = new Node(key,otherData);
        if(root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent; // 暂存父节点
            while(true) {
                parent = current;
                //放入左边的树中去,root.getKeyData() > key,这种写法有点问题,因为root节点是一个特殊的节点，
                //这里本应该是一个当前节点的概念，所以可以用一个变量来表示
                if(current.getKeyData() > key) {
                    current = current.getLeftNode();
                    if(current == null) {
                        parent.setLeftNode(newNode);
                        break;
                    }
                } else {// 放入右边的树中去
                    current = current.getRightNode();
                    if(current == null) {
                        parent.setRightNode(newNode);
                        break;
                    }
                }
            }
        }
    }

    public void delete(int key) {

    }

    public void change(int key,int newKey) {

    }

    public Node find(int key) {
        if(root == null) {
            return null;
        }

        Node current = root;
        while (true) {
            if(current.getKeyData() > key) { //查找左子树
                current = current.getLeftNode();
                if(current == null) {
                    return null;
                }
            } else if(current.getKeyData() < key) { // 查找有子树
                current = current.getRightNode();
                if(current == null) {
                    return null;
                }
            } else {
                return current;
            }
        }
    }

    /**
     * 展示
     * @param node
     */
    public void display(Node node) {
        System.out.println(node.getKeyData());
    }

    /**
     * 先序遍历：
     *  先输出中间的节点
     *  然后遍历左树
     *  再遍历右树
     *
     */
    public void preTraversing() {
        preTraversing0(root);
    }

    public void preTraversing0(Node node) {
        if(node != null) {
            System.out.println(node.getKeyData());
            preTraversing0(node.getLeftNode());
            preTraversing0(node.getRightNode());
        }
    }

    /**
     * 求树的最大高度
     * @param node
     * @return
     */
    public int height(Node node) {
        if(node != null) {
            return Math.max(height(node.getLeftNode()),height(node.getRightNode())) + 1;
        }
        return 0;
    }

}

package org.sheamus.datastructure.array;

/**
 * 数组的封装类
 */
public class MyArray {
    /**
     * 数组
     */
    private int[] arr;
    /**
     * 有效数组的大小
     */
    private int size;
    /**
     * 数组的默认大小
     */
    private static final int DEFAULT_VALUE = 10;

    public MyArray() {
        arr = new int[DEFAULT_VALUE];
    }

    public MyArray(int capacity) {
        arr = new int[capacity];
    }

    /**
     * 插入数据
     *
     * @param value
     */
    public void insert(int value) throws Exception {
        if (size >= arr.length) {
            throw new Exception("扩容吧，没有空间了");
        }
        arr[size++] = value;
    }

    /**
     * 查询目标数据，当未查询到的时候返回-1，当查询到返回下标
     *
     * @param target
     * @return
     */
    public int find(int target) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除查询到的第一个数字
     *
     * @param deleteKey
     * @return
     */
    public boolean delete(int deleteKey) {
        if (find(deleteKey) == -1) {
            return false;
        }
        for (int i = find(deleteKey); i < size; i++) {
            arr[i] = arr[i + 1];
        }
        return true;
    }

    /**
     * 显示数组中的数据
     */
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

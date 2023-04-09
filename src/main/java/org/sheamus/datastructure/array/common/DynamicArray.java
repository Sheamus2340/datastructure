package org.sheamus.datastructure.array.common;

import java.util.Iterator;

/**
 * 动态通用数据类型数组：
 * 1. 支持所有类型；
 * 2. 动态扩、缩容；
 */
public class DynamicArray<T> implements Iterator<T>, Iterable<T> {
    // 数组
    private T[] data;
    // 数组的大小
    private int size;
    // 当前索引
    private int index;
    // 默认大小
    private final static int DEFAULT_SIZE = 16;

    public DynamicArray() {
        data = (T[]) new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
    }

    public DynamicArray(int size) {
        data = (T[]) new Object[size];
        this.size = size;
    }

    /**
     * 添加元素
     * 扩容
     *
     * @param t
     */
    public void add(T t) {
        if (index == size - 1) {
            // 扩容
            resize(data);
        }
        data[index++] = t;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    int cur = 0;

    @Override
    public boolean hasNext() {
        if (cur < size) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        return data[cur++];
    }

    /**
     * 删除元素并缩容
     *
     * @return
     */
    public void remove() {
        if (index == 0) {
            throw new IndexOutOfBoundsException();
        }
        // 注意这里需要--index，因为添加的时候是index++, index已经不是数组的下标了
        data[--index] = null;
    }

    /**
     * 扩容 2 倍
     *
     * @param dataParameter
     */
    private void resize(T[] dataParameter) {
        int sizeTmp = 2 * size;
        T[] dataTmp = (T[]) new Object[sizeTmp];

        // 复制
        for (int i = 0; i < size; i++) {
            dataTmp[i] = dataParameter[i];
        }
        size = sizeTmp;
        data = dataTmp;
    }


    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray(5);

        for (int i = 0; i < 17; i++) {
            dynamicArray.add(i);
        }

        System.out.println(dynamicArray.size + " " + dynamicArray.index);

        dynamicArray.remove();

        Iterator<Integer> iterator = dynamicArray.iterator();

        while (iterator.hasNext()) {
            System.out.println(dynamicArray.next());
        }


    }

}

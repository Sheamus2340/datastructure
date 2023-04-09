package org.sheamus.datastructure.stack.array;

public class Stack<T> {
    private int size;
    private T[] data;

    private int index = 0;

    public Stack() {}

    public Stack(int size) {
        data = (T[]) new Object[size];
    }

    public void push(T value) {
        if (index == size - 1) {
            throw new IndexOutOfBoundsException();
        }

        data[index++] = value;
    }

    public T pop() {
        if (index == 0) {
            throw new IndexOutOfBoundsException();
        }

        return data[--index];
    }

    public static void main(String[] args) {
        Stack stack = new Stack(3);
        stack.push(1);

        stack.push(2);

        stack.push(3);


        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
        }

    }

}

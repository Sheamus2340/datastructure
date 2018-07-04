package org.sheamus.datastructure.array;

/**
 * Created by Sheamus on 2018/7/4.
 */
public class MyArrayTest {
    public static void main(String[] args) throws Exception {
        MyArray arr = new MyArray();
        arr.insert(12);
        arr.insert(11);
        arr.insert(16);
        arr.insert(19);
        arr.insert(22);
        arr.insert(21);
        arr.display();
        arr.delete(16);
        arr.display();
    }
}

package org.sheamus.concurrency.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * title：
 */
public class MyThreadTest {

    private static volatile List<String> set = new ArrayList<>();

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i <= 5; i++) {
                    if (i == 5) {
                        lock.notifyAll();
                    }
                    for (int j = 0; j < 3; j++) {
                        set.add("A:" + String.valueOf('a' + i));
                    }
                }
            }

        }, "A");

        Thread b = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 3; j++) {
                        set.add("B:" + String.valueOf('a' + i));
                    }

                }
            }
        }, "B");

        b.start();
        a.start();


        a.join();
        b.join();

        print(set);

    }

    private static void print(List<String> set) {

        if (set.isEmpty()) {
            return;
        }

        for (String str : set) {
            System.out.print(str + "\t");
        }

    }

}

package org.sheamus.concurrency.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * title：
 */
public class ReentrantLockTest {

    private static volatile int num = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void addNum() {
        lock.lock();

        num++;

        lock.unlock();
    }

    public static void main(String[] args) {

        for (int i = 0; i <= 100; i++) {
            new Thread(ReentrantLockTest::addNum).start();
        }

        System.out.println(num);

    }

}

package org.sheamus.concurrency.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * title：
 */
public class LockSupportTest {

    private void printA(Thread thread) {
        System.out.println("A");
        // 释放 B 线程的许可
        LockSupport.unpark(thread);
    }

    private void printB(Thread thread) {
        // 阻塞，没有拿到当前线程B的许可
        LockSupport.park();
        System.out.println("B");
        // 释放 C 线程的许可
        LockSupport.unpark(thread);
    }

    private void printC() {
        // 阻塞，没有拿到当前线程C的许可
        LockSupport.park();
        System.out.println("C");
    }

    public static void main(String[] args) {
        LockSupportTest lockSupportTest = new LockSupportTest();

        Thread t3 = new Thread(lockSupportTest::printC);
        t3.start();

        Thread t2 = new Thread(() -> {
            lockSupportTest.printB(t3);
        });
        t2.start();

        Thread t1 = new Thread(() -> {
            lockSupportTest.printA(t2);
        });
        t1.start();
    }

}

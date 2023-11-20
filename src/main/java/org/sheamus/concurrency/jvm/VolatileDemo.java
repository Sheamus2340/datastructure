package org.sheamus.concurrency.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 的demo，主要是看 volatile 的使用
 */
public class VolatileDemo {

    int num = 0;

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            Thread thread = new Thread(task, "Thread-" + i);
            thread.start();
            num++;
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        VolatileDemo demo = new VolatileDemo();
//        demo.run();
//    }

    class Task implements Runnable {

        @Override
        public void run() {
            num++;
            System.out.println(Thread.currentThread().getName() + ", num is " + num);
        }
    }

    //公共变量, volatile 修饰会有问题，++count 不是原子操作，极端条件会有问题
    volatile int count = 0;

    public static void main(String[] args) {
        //new一个实现Runnable的类
        VolatileDemo test = new VolatileDemo();
        //创建1个任务
        MyRunnable myRunnable1 = test.new MyRunnable();
        //创建5个线程
        for (int i = 0; i < 4; i++) {
            new Thread(myRunnable1).start();
        }
    }

    //创建一个实现Runnable的类
    class MyRunnable implements Runnable {
        public void run() {
            while (true) {
                //锁住的是同一对象
                //synchronized (this) {
                    if (count >= 1000) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ":count:" + (++count));
                    // 测试时，线程更容易切换
                    Thread.yield();
                //}

            }
        }
    }

}



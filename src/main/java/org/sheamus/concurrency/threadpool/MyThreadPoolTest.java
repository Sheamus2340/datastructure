package org.sheamus.concurrency.threadpool;

import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2, 2, 3, 1, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            int t = i;
            MyThreadPool.Task task = new MyThreadPool.Task("Task" + t);
            myThreadPool.execute(task);
        }

    }
}

package org.sheamus.concurrency.multhread;

public class PrintThread implements Runnable {

    private String name;
    private MyCounter myCounter;

    public PrintThread(MyCounter myCounter, String name) {
        this.myCounter = myCounter;
        this.name = name;
    }

    @Override
    public void run() {
        while (myCounter.num <= 100) {
            // 1 2 3 | 4
            synchronized (myCounter) {
                int nameValue = Integer.parseInt(name);
                if (myCounter.num % 3 == nameValue) {
                    System.out.println("线程 " + name + " 打印：" + myCounter.num + "  ==  " + myCounter.num % 3);
                    myCounter.num++;
                    myCounter.notifyAll();
                }
                try {
                    myCounter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 计数器
 */
class MyCounter {
    public volatile int num = 1;
}

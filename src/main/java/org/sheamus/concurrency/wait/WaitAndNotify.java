package org.sheamus.concurrency.wait;

/**
 * title：
 */
public class WaitAndNotify {

    //Object object = new Object();

    public void waitMethod() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void notifyMethod() {
        synchronized (this) {
            this.notifyAll();
        }
    }

    public void firstPrint() {
        System.out.println("111");
        waitMethod();
        System.out.println("333");
    }

    public void secondPrint() {
        System.out.println("222");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyMethod();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        Thread t1 = new Thread(waitAndNotify::firstPrint);

        Thread t2 = new Thread(waitAndNotify::secondPrint);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }


}

package org.sheamus.concurrency.jvm;

/**
 * title：
 */
public class MultipVolatileDemo {

    private static volatile int n = 0;

    public static synchronized void add() {
        n++;
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < 200; i++) {
                add();
            }

        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        while (n < 100) {

        }
        System.out.println("stop!" + n);
    }

}

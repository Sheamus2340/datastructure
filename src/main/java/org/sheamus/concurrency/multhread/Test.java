package org.sheamus.concurrency.multhread;

public class Test {

    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        Thread thread1 = new Thread(new PrintThread(myCounter, "0"));
        Thread thread2 = new Thread(new PrintThread(myCounter, "1"));
        Thread thread3 = new Thread(new PrintThread(myCounter, "2"));

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

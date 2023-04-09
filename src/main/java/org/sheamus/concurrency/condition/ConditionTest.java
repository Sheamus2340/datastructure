package org.sheamus.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 需要和 ReentrantLock 配合使用
 * Condition 可以理解为 线程为waiting 状态的线程容器
 */
public class ConditionTest {

    static ReentrantLock lock = new ReentrantLock();
    // 是否有假期的标识
    static volatile boolean vacation = false;
    // 是否有钱的标识
    static volatile boolean money = false;
    // 假期的wait队列
    static Condition vacationCondition = lock.newCondition();
    // 钱的wait队列
    static Condition moneyCondition = lock.newCondition();
    // 线程名称
    static String jackName = "Jack";
    static String roseName = "Rose";
    static String boss = "BOSS";

    public static void work() {
        lock.lock();
        if (Thread.currentThread().getName().equals(roseName)) {
            // 为什么需要使用while，因为 signalAll 之后是不会再判断 vacation 条件了，导致业务含义失效
            while (!vacation) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 没有假期不工作.....");
                    vacationCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // while 循环之后再执行
            System.out.println(Thread.currentThread().getName() + " ------有假期工作开始------");
        }
        if (Thread.currentThread().getName().equals(jackName)) {
            while (!money) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 没有钱不工作.....");
                    moneyCondition.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " ------有钱工作开始------");
        }
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            work();
        }, jackName).start();

        new Thread(() -> {
            work();
        }, roseName).start();

        Thread.sleep(2000);

        new Thread(() -> {
            lock.lock();
            vacation = true;
            money = true;
            vacationCondition.signalAll();
            moneyCondition.signalAll();
            lock.unlock();
        }, boss).start();
    }

}

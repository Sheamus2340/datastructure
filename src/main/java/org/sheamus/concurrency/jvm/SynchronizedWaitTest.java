package org.sheamus.concurrency.jvm;

/**
 * synchronized 对资源加锁操作，资源可以是类（字节码）也可以是对象（堆的对象），注意：只有当对象或者类加上 synchronized 之后才会对应一个monitor对象
 * 本质是 类（字节码）或者 对象（堆的对象）对应的 monitor 对象中的属性怎么处理的问题
 * 核心包含下面3个属性：
 * entry_set : 存放竞争锁的所有线程
 * waiting_set : 存放线程是 waiting 状态的线程
 * <p>
 * Object.wait() 是会释放锁的
 */
public class SynchronizedWaitTest {
    // 锁对象
    static Object lock = new Object();
    // 是否有假期的标识
    static volatile boolean vacation = false;
    // 是否有钱的标识
    static volatile boolean money = false;
    // 线程名称
    static String jackName = "Jack";
    static String roseName = "Rose";
    static String boss = "BOSS";

    public static void work() throws InterruptedException {
        synchronized (lock) {
            if (Thread.currentThread().getName().equals(jackName)) {
                while (!vacation) {
                    System.out.println(Thread.currentThread().getName() + " 没有假期不工作.....");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " ------有假期工作开始------");
            }

            if (Thread.currentThread().getName().equals(roseName)) {
                while (!money) {
                    System.out.println(Thread.currentThread().getName() + " 没有钱不工作.....");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " ------有钱工作开始------");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, jackName).start();

        new Thread(() -> {
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, roseName).start();

        new Thread(() -> {
            synchronized (lock) {
                vacation = true;
                money = true;
                lock.notifyAll();
            }
        }, boss).start();
    }

}

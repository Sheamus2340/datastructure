package org.sheamus.concurrency.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个阻塞队列
 *
 * @param <T>
 */
public class BlockingQueue<T> {

    // 队列
    private Deque<T> deque = new ArrayDeque<>();
    // 队列大小
    private int count = 0;

    // 当前队列的元素个数
    private volatile int curSize = 0;
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 空条件
    private Condition emptyCondition = lock.newCondition();
    // 满条件
    private Condition fullCondition = lock.newCondition();

    // 主线程的锁
    private static ReentrantLock mainLock = new ReentrantLock();
    // 队列非空
    private static Condition notEmptyCondition = mainLock.newCondition();

    public BlockingQueue(int count) {
        this.count = count;
    }

    /**
     * 队列元素的个数
     *
     * @return
     */
    public int size() {
        lock.lock();
        try {
            return curSize;
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @return
     */
    public boolean isFull() {
        lock.lock();
        try {
            return curSize == count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞队列的获取方法
     *
     * @return
     */
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                System.out.println("[take]" + Thread.currentThread().getName() + "线程，阻塞队列为空, 阻塞");
                emptyCondition.await();
            }
            T t = deque.removeFirst();
            System.out.println("[take]" + Thread.currentThread().getName() + "线程消费阻塞队列中的元素 " + t);
            curSize--;
            fullCondition.signalAll();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    /**
     * 阻塞队列的超时获取方法
     *
     * @return
     */
    public T take(long timeOut, TimeUnit timeUnit) {
        lock.lock();
        long toNanos = timeUnit.toNanos(timeOut);
        try {
            while (deque.isEmpty()) {
                // 判断 toNanos 是否没有时间了
                if (toNanos <= 0) {
                    System.out.println("[take timeout]" + Thread.currentThread().getName() + " 线程，阻塞队列为空, 阻塞 -- 超时时间到");
                    return null;
                }
                System.out.println("[take timeout]" + Thread.currentThread().getName() + " 线程，阻塞队列为空, 阻塞 -- 超时等待" + toNanos);
                // 注意这里需要将 toNanos 的值进行重置，因为整个是一个循环，
                // nanos的值需要被修改，要不然每次都是休眠 toNanos 时长
                toNanos = emptyCondition.awaitNanos(toNanos);
            }
            T t = deque.removeFirst();
            System.out.println("[take timeout]" + Thread.currentThread().getName() + " 线程消费阻塞队列中的元素 " + t);
            curSize--;
            fullCondition.signalAll();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    /**
     * 阻塞队列的添加方法
     *
     * @param t
     */
    public void put(T t) {
        lock.lock();
        try {
            while (deque.size() == count) {
                System.out.println("[put]" + Thread.currentThread().getName() + " 阻塞队列满了, 阻塞任务 " + t);
                fullCondition.await();
            }
            deque.addFirst(t);
            System.out.println("[put]" + Thread.currentThread().getName() + " 向阻塞队列中添加任务 " + t);
            curSize++;
            emptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue(10);

        new Thread(() -> {
            Integer take = blockingQueue.take();
            Integer take1 = blockingQueue.take();
            Integer take2 = blockingQueue.take();
            System.out.println(take + " " + take1 + " " + take2 + " " + blockingQueue.size());
        }, "take").start();

        new Thread(() -> {
            blockingQueue.put(1);
            blockingQueue.put(2);
            blockingQueue.put(3);
            blockingQueue.put(11);
            blockingQueue.put(22);
            blockingQueue.put(33);
        }, "put").start();

        // 当队列非空时，把主线程置为 waiting 状态
        mainLock.lock();
        try {
            while (blockingQueue.size() > 0) {
                notEmptyCondition.await();
            }
        } finally {
            mainLock.unlock();
        }

    }

}

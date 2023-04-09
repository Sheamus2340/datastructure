package org.sheamus.concurrency.threadpool;

import org.sheamus.concurrency.queue.BlockingQueue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * 实现自定义的线程池
 */
public class MyThreadPool {
    Object lock = new Object();
    // 装所有线程的容器, 工作的线程
    private HashSet<Worker> workers = new HashSet<>();
    // 核心线程数
    private int coreSize;
    // 最大线程数
    private int maxSize;
    // 阻塞队列, 存放任务
    private BlockingQueue<Runnable> blockingQueue;
    //超时时间 + 单位
    private long timeOut;
    private TimeUnit timeUnit;

    /**
     * 构造函数
     *
     * @param coreSize
     * @param count
     * @param timeOut
     * @param timeUnit
     */
    public MyThreadPool(int coreSize, int maxSize, int count, long timeOut, TimeUnit timeUnit) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.blockingQueue = new BlockingQueue<>(count);
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
    }

    private void createThread(int threadSize) {
        // 创建核心线程数放入容器中
        for (int i = 0; i < threadSize; i++) {
            Worker worker = new Worker("worker-" + i);
            workers.add(worker);
        }
    }

    /**
     * 从 workers 中获取空闲的线程
     *
     * @return
     */
    private Worker getFreeThread() {
        if (workers.isEmpty()) {
            createThread(coreSize);
        }
        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker next = iterator.next();
            if (next.getState().equals(Thread.State.NEW)) {
                return next;
            }
        }
        return null;
    }

    public void execute(Runnable task) {
        synchronized (lock) {
            // 任务来了，获取 workers 中的线程进行执行
            if (workers.isEmpty()) {
                createThread(coreSize);
            }

            // 判断核心线程数是否都空闲
            Worker freeThread = getFreeThread();
            if (freeThread != null) {
                freeThread.setTask(task);
                freeThread.start();
                return;
            }
            // 如果不空闲，判断阻塞队列是否满了
            // 没有满，需要将任务加入阻塞队列
            //if (!blockingQueue.isFull()) {
            blockingQueue.put(task);
            //    return;
            // }
            // 满了，再创建最大线程池数
            // createThread(maxSize - coreSize);
            // 如果最大线程也都没有空闲
//            Worker freeMaxThread = getFreeThread();
//            if (freeMaxThread != null) {
//                freeMaxThread.setTask(task);
//                freeMaxThread.start();
//                return;
//            }
            // 触发拒绝策略
        }
    }


    class Worker extends Thread {
        // 任务
        Runnable task;

        public Worker(String name) {
            super(name);
        }

        public Worker(Runnable task) {
            this.task = task;
        }

        public void setTask(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            super.run();
            // 任务不为空时，进行任务的执行，因为take获取任务是一个阻塞型的操作，所以线程池会被阻塞住
            while (task != null || (task = blockingQueue.take(timeOut, timeUnit)) != null) {
                task.run();
                // 任务执行完成置为空，当前任务执行完成之后需要向阻塞队列中获取任务
                task = null;
            }
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }

    public static class Task implements Runnable {
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(name + " 任务被【" + Thread.currentThread().getName() + "】线程执行完成 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

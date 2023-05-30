package org.sheamus.concurrency.future;

import java.util.concurrent.*;

/**
 * 创建一个自定义的线程池
 */
public class CustomThreadPool {

    private int corePoolSize = 5;
    private int maximumPoolSize = 10;
    private long keepAliveTime = 60;
    private TimeUnit unit = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10000);

    public CustomThreadPool() {
    }

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.workQueue = workQueue;
    }

    public ThreadPoolExecutor createCustomThreadPool() {
        ThreadFactory threadFactory = new CustomThreadFactory("CustomThreadPool");

        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}

package org.sheamus.concurrency.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建公共的线程池
 */
public class CommonThreadPool {

    public ThreadPoolExecutor createCommonThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000), new CustomThreadFactory("commonThreadPool"), new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }
}

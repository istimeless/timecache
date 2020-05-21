package com.istimeless.timecachecommon.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolUtil {

    private static ExecutorService executorService =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors() * 2,
                    10,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(10000),
                    new ThreadFactoryBuilder().setNameFormat("timecache-thread-%s").build(),
                    new ThreadPoolExecutor.AbortPolicy());

    private static ScheduledThreadPoolExecutor  scheduledExecutorService =
            new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    new ThreadFactoryBuilder().setNameFormat("timecache-scheduled-thread-%s").build(),
                    new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static ScheduledThreadPoolExecutor getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}

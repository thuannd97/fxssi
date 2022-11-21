package com.thuannd.crawler.fxssi.executor;

import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ExecutorJob implements DisposableBean {

    protected ScheduledExecutorService scheduledExecutorService;

    public ExecutorJob(int delay) {
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.scheduledExecutorService.scheduleWithFixedDelay(this::execute, 10, delay, TimeUnit.SECONDS);
    }

    public abstract void execute();

    @Override
    public void destroy() {
        scheduledExecutorService.shutdown();
    }
}

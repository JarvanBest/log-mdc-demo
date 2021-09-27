package com.jarvan.demo.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 * Created by zjw on 2021/9/27 16:54
 */
public class Test {

    public static void main(String[] args) throws Exception {
        test1();

    }

    public static void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        context.set("test code");

        Runnable ttlRunnable = TtlRunnable.get(() -> System.out.println(context.get()));
        executorService.submit(ttlRunnable);
    }

    public static void test1() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = TtlExecutors.getTtlExecutorService(executorService);


        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("test value");

        Runnable task = () -> System.out.println("Runnable" + context.get());
        Callable call = () -> "Callable" + context.get();
        executorService.submit(task);
        executorService.submit(call);


        // --------------------------------------
        System.out.println(call.call());
        System.out.println(context.get());
    }
}

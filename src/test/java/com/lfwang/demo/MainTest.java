package com.lfwang.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanglingfeng
 * @date Created in 2021/9/27
 */
public class MainTest {

    public static void main(String[] args) {
        ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newCachedThreadPool());
        ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();

        threadLocal.set(1);
        System.out.println(Thread.currentThread().getName() + " - " + threadLocal.get());

        pool.execute(() ->System.out.println(Thread.currentThread().getName() + " - " + threadLocal.get()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadLocal.set(2);
        System.out.println(Thread.currentThread().getName() + " - " + threadLocal.get());

        pool.execute(() ->System.out.println(Thread.currentThread().getName() + " - " + threadLocal.get()));
        pool.shutdown();
    }
}

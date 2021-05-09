package com.bball.cache.redisson;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用redisson实现分布式redis锁
 */
public class DistributedLockDemo {
    private static volatile int count = 0;
    private static RLock lock = null;
    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6376");
        RedissonClient client = Redisson.create(config);
        lock = client.getLock("myLock");
    }

    @SneakyThrows
    public static void main(String[] args) {
        int threadNum = 5;
        final CountDownLatch latch = new CountDownLatch(threadNum);
        // 多线程并发做计数累计，模拟分布式系统做累计
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> increment(latch)).start();
        }
        latch.await();
        System.out.println("累计后计数器等于：" + count);
    }

    @SneakyThrows
    private static void increment(CountDownLatch latch ){
        for (int i = 0;i < 1000; i++) {
            lock();
            count++;
            unlock();
            Thread.yield();
        }
        latch.countDown();
    }

    /**
     * 分布式锁加锁
     */
    private static void lock() {
        // 设置获得锁60秒后过期，防止锁不能释放
        lock.lock(60, TimeUnit.SECONDS);
    }

    /**
     * 分布式锁解锁
     */
    private static void unlock() {
        lock.unlock();
    }
}

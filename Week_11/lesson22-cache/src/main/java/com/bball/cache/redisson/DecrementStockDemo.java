package com.bball.cache.redisson;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

/**
 * 使用redisson的RAtomicLong模拟分布式减库存操作
 */
public class DecrementStockDemo {
    private static RAtomicLong atomicLong = null;
    private static ForkJoinPool pool = new ForkJoinPool(10);

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6376");
        RedissonClient client = Redisson.create(config);
        atomicLong = client.getAtomicLong("myAtomicLong");
        atomicLong.set(10000);
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("初始库存：" + atomicLong.get());
        int threadNum = 8;
        CountDownLatch latch = new CountDownLatch(threadNum);
        // 模拟分布式减库存
        for (int i = 0; i < threadNum; i++) {
            pool.submit(() -> decrementStock(latch, 1000));
        }
        latch.await();
        // 计数器可能为负数，返回库存数要做判断，如果为负数则返回0
        System.out.println("库存剩余：" + (atomicLong.get() < 0 ? 0 : atomicLong.get()));
    }

    /**
     * 模拟减库存
     * @param latch
     * @param inventoryNum
     */
    private static void decrementStock(CountDownLatch latch, int inventoryNum) {
        try {
            for (int i = 0; i < inventoryNum; i++) {
                decrement();
            }
        } finally {
            latch.countDown();
        }
    }

    private static void decrement() {
        if (atomicLong.get() > 0) {
            if(atomicLong.decrementAndGet() < 0) {
                System.out.println("库存不足");
                throw new RuntimeException("库存不足");
            }
        } else {
            System.out.println("库存不足");
            throw new RuntimeException("库存不足");
        }
    }
}

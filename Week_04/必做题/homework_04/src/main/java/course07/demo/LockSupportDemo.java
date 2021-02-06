package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    private static volatile Integer result;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            result = CalculateUtil.sum();
        }).start();

        while (result == null){
            LockSupport.parkNanos(TimeUnit.MICROSECONDS.toNanos(100));
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

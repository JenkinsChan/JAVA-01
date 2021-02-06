package course07.demo;

import course07.util.CalculateUtil;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(0);
    private static volatile Integer result;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            result = CalculateUtil.sum();
            semaphore.release();
        }).start();

        semaphore.acquire();
        System.out.println("异步计算结果为：" + result.intValue());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

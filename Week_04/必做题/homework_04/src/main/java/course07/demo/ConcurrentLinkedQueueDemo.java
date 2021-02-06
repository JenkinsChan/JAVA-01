package course07.demo;

import course07.util.CalculateUtil;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            queue.add(CalculateUtil.sum());
        }).start();

        Integer result;
        while ((result = queue.poll()) == null){
            Thread.yield();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

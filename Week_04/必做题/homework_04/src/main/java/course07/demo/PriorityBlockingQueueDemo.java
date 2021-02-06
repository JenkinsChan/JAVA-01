package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {
    private static PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            queue.put(CalculateUtil.sum());
        }).start();

        System.out.println("异步计算结果为：" + queue.take());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

package course07.demo;

import course07.util.CalculateUtil;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    private static SynchronousQueue<Integer> queue = new SynchronousQueue();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            try {
                queue.put(CalculateUtil.sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("异步计算结果为：" + queue.take());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

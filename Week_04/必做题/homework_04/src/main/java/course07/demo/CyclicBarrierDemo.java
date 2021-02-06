package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static volatile Integer result;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            result = CalculateUtil.sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        cyclicBarrier.await();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

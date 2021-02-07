package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> CalculateUtil.sum());
        System.out.println("异步计算结果为：" + future.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executorService.shutdown();
    }
}

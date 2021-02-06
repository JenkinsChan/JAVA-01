package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        FutureTask futureTask = new FutureTask(() ->{
            return CalculateUtil.sum();
        });

        new Thread(futureTask).start();
        System.out.println("异步计算结果为：" + futureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

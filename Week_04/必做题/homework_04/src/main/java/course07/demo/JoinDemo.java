package course07.demo;

import course07.util.CalculateUtil;

public class JoinDemo {
    private static volatile Integer result;
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t = new Thread(() -> {
            result = CalculateUtil.sum();
        });
        t.start();
        t.join();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

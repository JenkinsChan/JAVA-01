package course07.demo;

import course07.util.CalculateUtil;

public class ActiveThreadCountDemo {
    private static volatile Integer result;
    public static void main(String[] args) {
        int startThreadCount = Thread.activeCount();
        long start = System.currentTimeMillis();
        new Thread(() -> {
            result = CalculateUtil.sum();
        }).start();

        while (Thread.activeCount() > startThreadCount){
            Thread.yield();
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

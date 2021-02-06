package course07.demo;

import course07.util.CalculateUtil;

public class SleepDemo {
    private static volatile Integer result;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            result = CalculateUtil.sum();
        }).start();

        while (result == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

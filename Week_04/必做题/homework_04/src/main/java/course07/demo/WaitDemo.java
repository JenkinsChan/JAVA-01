package course07.demo;

import course07.util.CalculateUtil;

public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        MethodClass methodClass = new MethodClass();
        // 异步计算
        new Thread(() -> {
            methodClass.calculate();
        }).start();

        System.out.println("异步计算结果为：" + methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

class MethodClass{
    private static volatile Integer result;
    public synchronized int getResult() {
        while (result == null){
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public synchronized void calculate(){
        result = CalculateUtil.sum();
        notifyAll();
    }
}

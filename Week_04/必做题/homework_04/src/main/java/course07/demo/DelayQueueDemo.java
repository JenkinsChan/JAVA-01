package course07.demo;

import course07.util.CalculateUtil;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    private static DelayQueue<ResultObj> queue = new DelayQueue();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            int result = CalculateUtil.sum();
            queue.put(new ResultObj(result));
        }).start();

        System.out.println("异步计算结果为：" + queue.take().getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

class ResultObj implements Delayed{
    public ResultObj(Integer result) {
        this.result = result;
    }

    private volatile Integer result;
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        if(this == o){
            return 0;
        }
        if(o instanceof ResultObj){
            ResultObj other = (ResultObj) o;
            int diff = result - other.result;
            if(diff < 0){
                return -1;
            }else if(diff > 0){
                return 1;
            }else {
                return 0;
            }
        }

        return 0;
    }

    public Integer getResult() {
        return result;
    }
}
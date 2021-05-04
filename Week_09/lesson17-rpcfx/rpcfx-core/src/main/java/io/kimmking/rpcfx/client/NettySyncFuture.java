package io.kimmking.rpcfx.client;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.*;

@Data
public class NettySyncFuture implements Future {
    // 缓存future
    private static Map<Long, NettySyncFuture> FUTURES = new ConcurrentHashMap<>();
    // 默认超时时间10秒
    private static final int DEFAULT_READ_TIMEOUT = 10;
    private LinkedBlockingQueue<RpcfxResponse> futureQueue = new LinkedBlockingQueue<>();
    private RpcfxResponse response;
    private RpcfxRequest request;

    public NettySyncFuture(RpcfxRequest request) {
        this.request = request;
        // 实例化的时候把future加入缓存
        FUTURES.put(request.getRId(), this);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        if (response != null) {
            return true;
        }
        return false;
    }

    @Override
    public RpcfxResponse get() throws InterruptedException, ExecutionException {
        return get(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public RpcfxResponse get(long timeout, TimeUnit unit) throws InterruptedException{
        response = futureQueue.poll(timeout, unit);
        if (response == null){
            throw new RpcfxException("获取远程结果异常");
        }
        return response;
    }

    public static void callback(RpcfxResponse response){
        System.out.println("收到netty回调：" + response);
        NettySyncFuture future = FUTURES.remove(response.getRId());
        if (future != null){
            future.getFutureQueue().offer(response);
        }
    }
}

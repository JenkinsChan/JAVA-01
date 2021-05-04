package io.kimmking.rpcfx.client;

import java.util.concurrent.atomic.AtomicLong;

public class ClientHolder {
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);

    public static Long getInvokeId(){
        return INVOKE_ID.getAndIncrement();
    }
}

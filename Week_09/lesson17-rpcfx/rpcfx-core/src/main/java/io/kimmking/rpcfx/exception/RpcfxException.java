package io.kimmking.rpcfx.exception;

public class RpcfxException extends RuntimeException{
    public RpcfxException() {
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(String message) {
        super(message);
    }
}

package io.kimmking.rpcfx.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RpcfxResponse {
    private Long rId;
    private Object result;
    private boolean status;
    private Exception exception;

    public RpcfxResponse() {
    }

    public RpcfxResponse(Long rId) {
        this.rId = rId;
    }
}

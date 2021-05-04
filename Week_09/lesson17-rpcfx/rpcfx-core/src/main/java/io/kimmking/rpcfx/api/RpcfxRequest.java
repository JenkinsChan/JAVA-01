package io.kimmking.rpcfx.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcfxRequest implements Serializable {

  private long rId;
  private String serviceClass;
  private String method;
  private Object[] params;

  public RpcfxRequest() {
  }

  public RpcfxRequest(long rId) {
    this.rId = rId;
  }
}

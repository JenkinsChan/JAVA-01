package io.github.kimmking.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class MyHttpRequestFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        System.out.println("进入了我实现的过滤器");
        response.headers().set("set-cookie", "12345");
    }
}

package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import io.kimmking.rpcfx.exception.RpcfxException;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.CharsetUtil;
import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public final class Rpcfx {
    public static final String JSONTYPE = "application/json; charset=utf-8";

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    /**
     * 打开netty链接
     */
    private static void openConnect() throws InterruptedException {

    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) create(serviceClass, url, filter);

    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> AOP
//        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));
        // 使用cglib aop
        return RpcCglibProxy.getInstance(serviceClass, url, filters);
    }


    public static class RpcCglibProxy{
        public static <T> T getInstance(final Class<T> serviceClass, final String url, Filter... filters){
            Enhancer enhancer = new Enhancer();
            // 设置编辑的目标class
            enhancer.setSuperclass(serviceClass);
            // 设置回调方法
            enhancer.setCallback(new CglibMethodInterceptor(serviceClass, url, filters));
            return (T)enhancer.create();
        }
    }

    public static class CglibMethodInterceptor implements MethodInterceptor {
        private final Class<?> serviceClass;
        private final String url;
        private final Filter[] filters;

        public <T> CglibMethodInterceptor(Class<T> serviceClass, String url, Filter... filters) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.filters = filters;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest(ClientHolder.getInvokeId());
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            if (null != filters) {
                for (Filter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }

            RpcfxResponse response = null;
            try {
                // 调用netty发起请求
                response = post(request, url).get();
            } catch (Exception e) {
                System.out.println("rpc调用异常：" + e.getMessage());
                throw new RpcfxException("调用服务端异常", e);
            }

            if (!response.isStatus()) {
                throw new RpcfxException("服务端内部异常", response.getException());
            }

            return JSON.parse(response.getResult().toString());
        }
    }

    public static NettySyncFuture post(RpcfxRequest req, String url) throws URISyntaxException, UnsupportedEncodingException, InterruptedException {
        NettySyncFuture future = new NettySyncFuture(req);

        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        NettyHttpClientOutboundHandler handler = new NettyHttpClientOutboundHandler();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    // 维持通道可用
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // TCP数据包及时发送出去，关闭延迟
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //包含编码器和解码器
                            ch.pipeline().addLast(new HttpClientCodec());
                            // 聚合
                            ch.pipeline().addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                            // 解压
                            ch.pipeline().addLast(new HttpContentDecompressor());
                            // 指定处理器
                            ch.pipeline().addLast(handler);
                        }
                    });

            ChannelFuture f = b.connect("localhost",8081).sync();

            URI nettyUrl = new URI("/");
            //配置HttpRequest的请求数据和一些配置信息
            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_0, HttpMethod.POST, nettyUrl.toASCIIString(),
                    Unpooled.wrappedBuffer(reqJson.getBytes("UTF-8")));

            request.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, JSONTYPE)
                    //开启长连接
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //设置传递请求内容的长度
                    .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            f.channel().writeAndFlush(request);

            // 等待关闭channel
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully().sync();
        }
        return future;
    }

    @Data
    public static class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {
        private Object response;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            FullHttpResponse httpResponse = (FullHttpResponse) msg;
            ByteBuf content = httpResponse.content();
            HttpHeaders headers = httpResponse.headers();
            System.out.println("content:" + System.getProperty("line.separator") + content.toString(CharsetUtil.UTF_8));
            System.out.println("headers:" + System.getProperty("line.separator") + headers.toString());
            response = JSON.parseObject(content.toString(CharsetUtil.UTF_8), RpcfxResponse.class);

            System.out.println("respons:" + response);
            // 获取到结果后回调处理
            NettySyncFuture.callback((RpcfxResponse)response);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("netty exception:" + cause.getMessage());
        }
    }
}

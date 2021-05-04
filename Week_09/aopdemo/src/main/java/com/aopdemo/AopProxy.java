package com.aopdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopProxy {
    public static <T> T create(final Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(AopProxy.class.getClassLoader(), new Class[]{serviceClass}, new RpcInvokeInstanceHandler());
    }

    public static class RpcInvokeInstanceHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return "hello world";
        }


    }
}

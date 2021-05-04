package com.bball.cxf.service;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class MainService {
    public static void main(String[] args) {
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        jaxWsServerFactoryBean.setAddress("http://localhost:8081/cxfService");
        jaxWsServerFactoryBean.setServiceClass(CxfServiceImpl.class);
        Server server = jaxWsServerFactoryBean.create();
        server.start();
        System.out.println("WebService CXF服务启动 。。。。");
    }
}

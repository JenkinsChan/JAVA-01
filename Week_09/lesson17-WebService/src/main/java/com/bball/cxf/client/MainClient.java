package com.bball.cxf.client;

import com.bball.cxf.service.CxfService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MainClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8081/cxfService");
        jaxWsProxyFactoryBean.setServiceClass(CxfService.class);
        CxfService cxfService = (CxfService) jaxWsProxyFactoryBean.create();
        System.out.println(cxfService.sayHello("bball", 28));
    }

}

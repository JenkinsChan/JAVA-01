package com.bball.hmily.demo.dubbo.exchange.service;

import com.bball.hmily.demo.common.exchange.entity.Exchange;

public interface MatchmakingService {

    /**
     * 美元兑换人民币
     * @param exchange
     * @return
     */
    void exchangeUsdToCny(Exchange exchange);

    /**
     * 模拟用户A在try阶段异常
     * @param exchange
     */
    void mockWithAccountATryException(Exchange exchange);
}

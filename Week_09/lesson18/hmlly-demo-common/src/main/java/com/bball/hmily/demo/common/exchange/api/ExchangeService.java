package com.bball.hmily.demo.common.exchange.api;

import com.bball.hmily.demo.common.exchange.entity.Exchange;

public interface ExchangeService {

    /**
     * 美元兑换人民币
     * @return
     */
    Boolean exchangeUsdToCny(Exchange exchange);

    void mockWithAccountATryException(Exchange exchange);
}

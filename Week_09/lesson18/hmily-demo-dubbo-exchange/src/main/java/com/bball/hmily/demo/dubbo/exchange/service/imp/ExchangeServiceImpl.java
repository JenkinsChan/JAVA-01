package com.bball.hmily.demo.dubbo.exchange.service.imp;

import com.bball.hmily.demo.common.exchange.api.ExchangeService;
import com.bball.hmily.demo.common.exchange.entity.Exchange;
import com.bball.hmily.demo.common.exchange.mapper.ExchangeMapper;
import com.bball.hmily.demo.dubbo.exchange.service.MatchmakingService;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private ExchangeMapper exchangeMapper;
    private MatchmakingService matchmakingService;

    public ExchangeServiceImpl(ExchangeMapper exchangeMapper, MatchmakingService matchmakingService) {
        this.exchangeMapper = exchangeMapper;
        this.matchmakingService = matchmakingService;
    }

    @Override
    public Boolean exchangeUsdToCny(Exchange exchange) {
        exchangeMapper.save(exchange);
        matchmakingService.exchangeUsdToCny(exchange);
        return Boolean.TRUE;
    }

    @Override
    public void mockWithAccountATryException(Exchange exchange) {
        exchangeMapper.save(exchange);
        matchmakingService.mockWithAccountATryException(exchange);
    }
}

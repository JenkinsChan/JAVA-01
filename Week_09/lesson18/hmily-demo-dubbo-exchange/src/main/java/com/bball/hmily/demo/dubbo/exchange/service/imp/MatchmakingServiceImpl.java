package com.bball.hmily.demo.dubbo.exchange.service.imp;

import com.bball.hmily.demo.common.account.api.AccountAService;
import com.bball.hmily.demo.common.account.api.AccountBService;
import com.bball.hmily.demo.common.account.dto.AccountDto;
import com.bball.hmily.demo.common.exchange.entity.Exchange;
import com.bball.hmily.demo.common.exchange.enums.ExchangeStatusEnum;
import com.bball.hmily.demo.common.exchange.mapper.ExchangeMapper;
import com.bball.hmily.demo.dubbo.exchange.service.MatchmakingService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchmakingServiceImpl implements MatchmakingService {
    private ExchangeMapper exchangeMapper;
    private AccountAService accountAService;
    private AccountBService accountBService;

    @SuppressWarnings("all")
    public MatchmakingServiceImpl(ExchangeMapper exchangeMapper, AccountAService accountAService,
                                  AccountBService accountBService) {
        this.exchangeMapper = exchangeMapper;
        this.accountAService = accountAService;
        this.accountBService = accountBService;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmExchangeStatus", cancelMethod = "cancelExchangeStatus")
    public void exchangeUsdToCny(Exchange exchange) {
        updateExchange(exchange, ExchangeStatusEnum.EXCHANGEING);
        accountAService.exchangeUsdToCny(buildAccountADto(exchange));
        accountBService.exchangeCnyToUsd(buildAccountBDto(exchange));
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmExchangeStatus", cancelMethod = "cancelExchangeStatus")
    public void mockWithAccountATryException(Exchange exchange) {
        updateExchange(exchange, ExchangeStatusEnum.EXCHANGEING);
        accountAService.exchangeUsdToCnyTryException(buildAccountADto(exchange));
        accountBService.exchangeCnyToUsd(buildAccountBDto(exchange));
    }

    private void updateExchange(Exchange exchange, ExchangeStatusEnum statusEnum) {
        exchange.setStatus(statusEnum.getCode());
        exchangeMapper.update(exchange);
    }

    public void confirmExchangeStatus(Exchange exchange) {
        updateExchange(exchange, ExchangeStatusEnum.EXCHANGE_SUCCESS);
        log.info("======执行交易confirm完成=======");
    }

    public void cancelExchangeStatus(Exchange exchange) {
        updateExchange(exchange, ExchangeStatusEnum.EXCHANGE_FAIL);
        log.info("======执行交易cancel完成=======");
    }

    private AccountDto buildAccountADto(Exchange exchage) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(exchage.getOfferUserId());
        accountDto.setCnyAmount(exchage.getCnyAmount());
        accountDto.setUsdAmount(exchage.getUsdAmount());
        return accountDto;
    }

    private AccountDto buildAccountBDto(Exchange exchage) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(exchage.getTakeUserId());
        accountDto.setCnyAmount(exchage.getCnyAmount());
        accountDto.setUsdAmount(exchage.getUsdAmount());
        return accountDto;
    }
}

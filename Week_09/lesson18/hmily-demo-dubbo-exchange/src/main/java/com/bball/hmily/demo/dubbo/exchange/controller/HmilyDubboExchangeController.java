package com.bball.hmily.demo.dubbo.exchange.controller;

import com.bball.hmily.demo.common.exchange.api.ExchangeService;
import com.bball.hmily.demo.common.exchange.entity.Exchange;
import com.bball.hmily.demo.common.exchange.enums.ExchangeStatusEnum;
import com.bball.hmily.demo.common.exchange.enums.InitiateCurrencyEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange")
public class HmilyDubboExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("exchangeUsdToCny")
    @ApiOperation(value = "模拟美元兑换人民币")
    public String exchangeUsdToCny(@RequestParam("usdAmount") @ApiParam(value = "美元金额") Double usdAmount,
                                   @RequestParam("cnyAmount") @ApiParam(value = "人民币金额") Double cnyAmount) {
        exchangeService.exchangeUsdToCny(buildExchange(usdAmount, cnyAmount));
        return "success";
    }

    @PostMapping("mockWithAccountATryException")
    @ApiOperation(value = "模拟用户A在try阶段异常")
    public String mockWithAccountATryException(@RequestParam("usdAmount") @ApiParam(value = "美元金额") Double usdAmount,
                                               @RequestParam("cnyAmount") @ApiParam(value = "人民币金额") Double cnyAmount) {
        exchangeService.mockWithAccountATryException(buildExchange(usdAmount, cnyAmount));
        return "success";
    }

    private Exchange buildExchange(Double usdAmount, Double cnyAmount) {
        Exchange exchange = new Exchange();
        exchange.setId(IdWorkerUtils.getInstance().createUUID());
        exchange.setOfferUserId(10000L);
        exchange.setTakeUserId(20000L);
        exchange.setCnyAmount(new BigDecimal(cnyAmount));
        exchange.setUsdAmount(new BigDecimal(usdAmount));
        exchange.setStatus(ExchangeStatusEnum.NOT_EXCHANGE.getCode());
        exchange.setInitiateCurrency(InitiateCurrencyEnum.USD.getCode());
        return exchange;
    }

}

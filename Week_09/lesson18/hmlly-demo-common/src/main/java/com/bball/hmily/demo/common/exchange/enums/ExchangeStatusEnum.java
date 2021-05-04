package com.bball.hmily.demo.common.exchange.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeStatusEnum {
    NOT_EXCHANGE(1, "未交易"),
    
    EXCHANGEING(2, "交易中"),

    EXCHANGE_FAIL(3, "交易失败"),
    
    EXCHANGE_SUCCESS(4, "交易成功");

    private final int code;

    private final String desc;
}

package com.bball.hmily.demo.common.exchange.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发起方货币
 */
@Getter
@AllArgsConstructor
public enum InitiateCurrencyEnum {
    CYN("CNY", "人民币"),

    USD("USD", "美元");
    private String code;
    private String desc;
}

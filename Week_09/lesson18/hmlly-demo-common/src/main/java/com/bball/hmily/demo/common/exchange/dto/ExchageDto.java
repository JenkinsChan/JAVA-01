package com.bball.hmily.demo.common.exchange.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ExchageDto implements Serializable {

    private static final long serialVersionUID = 6957734749389133832L;
    /**
     * 兑换的人民币金额
     */
    private BigDecimal cnyAmount;
    /**
     * 兑换的美元金额
     */
    private BigDecimal usdAmount;
    /**
     * 交易发起方
     */
    private Long userId;
}

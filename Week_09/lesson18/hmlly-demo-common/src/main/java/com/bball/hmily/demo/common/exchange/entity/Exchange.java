package com.bball.hmily.demo.common.exchange.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Exchange implements Serializable {

    private static final long serialVersionUID = 6957734749389133832L;
    private Long id;
    /**
     * 交易状态，1：未交易，2：交易中，3：交易失败，4：交易成功
     */
    private Integer status;
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
    private Long offerUserId;
    /**
     * 交易接收方
     */
    private Long takeUserId;
    /**
     * 发起方的货币，cny：人民币，usd：美元
     */
    private String initiateCurrency;
}

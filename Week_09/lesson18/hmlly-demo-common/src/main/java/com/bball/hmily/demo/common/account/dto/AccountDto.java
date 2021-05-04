package com.bball.hmily.demo.common.account.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 6966734749389133832L;
    private Long userId;
    private BigDecimal cnyAmount;
    private BigDecimal usdAmount;
}

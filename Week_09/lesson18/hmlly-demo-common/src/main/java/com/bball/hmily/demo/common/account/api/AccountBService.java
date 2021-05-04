package com.bball.hmily.demo.common.account.api;

import com.bball.hmily.demo.common.account.dto.AccountDto;
import org.dromara.hmily.annotation.Hmily;

public interface AccountBService {
    /**
     * 人民币兑换美元
     * @param dto
     * @return
     */
    @Hmily
    Boolean exchangeCnyToUsd(AccountDto dto);

    /**
     * 用户B兑换人民币异常
     * @param dto
     * @return
     */
    @Hmily
    Boolean exchangeCnyToUsdTryException(AccountDto dto);
}

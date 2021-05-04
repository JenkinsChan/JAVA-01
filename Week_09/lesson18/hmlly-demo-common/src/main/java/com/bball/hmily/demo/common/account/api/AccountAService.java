package com.bball.hmily.demo.common.account.api;

import com.bball.hmily.demo.common.account.dto.AccountDto;
import org.dromara.hmily.annotation.HmilyTCC;

public interface AccountAService {

    /**
     * 美元兑换人民币
     * @return
     */
    @HmilyTCC
    Boolean exchangeUsdToCny(AccountDto dto);

    /**
     * 模拟用户A在try阶段异常
     * @param dto
     * @return
     */
    @HmilyTCC
    Boolean exchangeUsdToCnyTryException(AccountDto dto);
}

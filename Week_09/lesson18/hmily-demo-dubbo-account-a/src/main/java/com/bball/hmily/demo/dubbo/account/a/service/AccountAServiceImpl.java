package com.bball.hmily.demo.dubbo.account.a.service;

import com.bball.hmily.demo.common.account.AccountException;
import com.bball.hmily.demo.common.account.api.AccountAService;
import com.bball.hmily.demo.common.account.dto.AccountDto;
import com.bball.hmily.demo.common.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

@Service("accountAService")
@Slf4j
public class AccountAServiceImpl implements AccountAService {
    private AccountMapper accountMapper;

    public AccountAServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean exchangeUsdToCny(AccountDto dto) {
        accountMapper.freezeUsdCurrency(dto);
        return null;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean exchangeUsdToCnyTryException(AccountDto dto) {
        throw new AccountException("用户A在try阶段异常");
    }

    public Boolean confirm(AccountDto dto){
        accountMapper.confirmUsdToCny(dto);
        log.debug("========执行元兑换人民币confirm方法完成========");
        return Boolean.TRUE;
    }

    public Boolean cancel(AccountDto dto){
        accountMapper.cancelUsdToCny(dto);
        log.debug("========执行美 元兑换人民币cancel方法完成========");
        return Boolean.TRUE;
    }
}

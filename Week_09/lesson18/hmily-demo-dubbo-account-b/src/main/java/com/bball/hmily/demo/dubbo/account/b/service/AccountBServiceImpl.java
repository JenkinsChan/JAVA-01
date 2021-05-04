package com.bball.hmily.demo.dubbo.account.b.service;

import com.bball.hmily.demo.common.account.AccountException;
import com.bball.hmily.demo.common.account.api.AccountBService;
import com.bball.hmily.demo.common.account.dto.AccountDto;
import com.bball.hmily.demo.common.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountBService")
@Slf4j
public class AccountBServiceImpl implements AccountBService {
    private AccountMapper accountMapper;

    public AccountBServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean exchangeCnyToUsd(AccountDto dto) {
        accountMapper.freezeCnyCurrency(dto);
        return null;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional(rollbackFor = Exception.class)
    public Boolean exchangeCnyToUsdTryException(AccountDto dto) {
        accountMapper.freezeCnyCurrency(dto);
        throw new AccountException("用户B兑换美元异常");
    }

    public Boolean confirm(AccountDto dto){
        accountMapper.confirmCnyToUsd(dto);
        log.info("========执行人民币兑换美元confirm方法完成========");
        return Boolean.TRUE;
    }

    public Boolean cancel(AccountDto dto){
        accountMapper.cancelCnyToUsd(dto);
        log.info("========执行人民币兑换美元cancel方法完成========");
        return Boolean.TRUE;
    }
}

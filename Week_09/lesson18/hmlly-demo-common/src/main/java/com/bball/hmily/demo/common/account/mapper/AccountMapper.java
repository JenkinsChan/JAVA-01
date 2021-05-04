package com.bball.hmily.demo.common.account.mapper;

import com.bball.hmily.demo.common.account.dto.AccountDto;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface AccountMapper {
    /**
     * 冻结人民账户
     * @param dto
     * @return
     */
    @Update("update account set cny_balance = cny_balance - #{cnyAmount}, " +
            "cny_freeze_amount = cny_freeze_amount + #{cnyAmount}, update_time = now() " +
            "where user_id = #{userId} and cny_balance >= #{cnyAmount}")
    int freezeCnyCurrency(AccountDto dto);

    /**
     * 冻结美元账户
     * @param dto
     * @return
     */
    @Update("update account set usd_balance = usd_balance - #{usdAmount}, " +
            "usd_freeze_amount = usd_freeze_amount + #{usdAmount}, update_time = now() " +
            "where user_id = #{userId} and usd_balance >= #{usdAmount}")
    int freezeUsdCurrency(AccountDto dto);

    /**
     * 确认人民币兑换美元成功
     * @param dto
     * @return
     */
    @Update("update account set cny_freeze_amount = cny_freeze_amount - #{cnyAmount}, " +
            "usd_balance = usd_balance + #{usdAmount}, update_time = now() where user_id = #{userId} " +
            "and cny_freeze_amount >= #{cnyAmount}")
    int confirmCnyToUsd(AccountDto dto);

    /**
     * 人民币兑换美元失败
     * @param dto
     * @return
     */
    @Update("update account set cny_balance = cny_balance + #{cnyAmount}, " +
            "cny_freeze_amount = cny_freeze_amount - #{cnyAmount},update_time = now() " +
            " where user_id = #{userId} and cny_freeze_amount >= #{cnyAmount}")
    int cancelCnyToUsd(AccountDto dto);

    /**
     * 确认美元兑换人民币成功
     * @param dto
     * @return
     */
    @Update("update account set usd_freeze_amount = usd_freeze_amount - #{usdAmount}, " +
            "cny_balance = cny_balance + #{cnyAmount}, update_time = now() where user_id = #{userId} " +
            "and usd_freeze_amount >= #{usdAmount}")
    int confirmUsdToCny(AccountDto dto);

    /**
     * 美元兑换人民币失败
     * @param dto
     * @return
     */
    @Update("update account set usd_balance = usd_balance + #{usdAmount}, " +
            "usd_freeze_amount = usd_freeze_amount - #{usdAmount},update_time = now() " +
            " where user_id = #{userId} and usd_freeze_amount >= #{usdAmount}")
    int cancelUsdToCny(AccountDto dto);
}

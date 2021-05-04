package com.bball.hmily.demo.common.exchange.mapper;

import com.bball.hmily.demo.common.exchange.entity.Exchange;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface ExchangeMapper {
    /**
     * 保存交易单
     * @param exchange
     * @return
     */
    @Insert("insert into exchange(id, status, offer_user_id, take_user_id, cny_amount," +
            "usd_amount, initiate_currency, create_time, update_time) " +
            "values(#{id}, #{status}, #{offerUserId}, #{takeUserId}, #{cnyAmount}, " +
            "#{usdAmount}, #{initiateCurrency}, now(), now())")
    int save(Exchange exchange);

    /**
     * 更新交易单
     * @param exchange
     * @return
     */
    @Update("update exchange set status = #{status} where id = #{id}")
    int update(Exchange exchange);
}

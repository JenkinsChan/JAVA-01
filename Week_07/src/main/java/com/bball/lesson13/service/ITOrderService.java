package com.bball.lesson13.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bball.lesson13.entity.TOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-03-11
 */
public interface ITOrderService extends IService<TOrder> {
    void batchInsertOrder();
}

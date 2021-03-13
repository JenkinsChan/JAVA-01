package bball.service;

import bball.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

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

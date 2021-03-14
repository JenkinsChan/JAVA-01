package bball.service;

import bball.entity.TOrder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bball
 * @since 2021-03-14
 */
public interface ITOrderService extends IService<TOrder> {
    List<TOrder> queryList(Wrapper<TOrder> wrapper);

    int deleteOrder(Wrapper<TOrder> wrapper);

    void saveOrderList(List<TOrder> orderList);
}

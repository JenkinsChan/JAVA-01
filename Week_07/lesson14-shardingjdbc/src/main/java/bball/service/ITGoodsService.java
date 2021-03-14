package bball.service;

import bball.entity.TGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-03-13
 */
public interface ITGoodsService extends IService<TGoods> {
    public void insertGoods(TGoods goods);

    public TGoods selectSlaveGoods(Long id);
}

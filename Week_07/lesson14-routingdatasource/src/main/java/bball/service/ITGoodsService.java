package bball.service;

import com.baomidou.mybatisplus.extension.service.IService;
import bball.entity.TGoods;

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

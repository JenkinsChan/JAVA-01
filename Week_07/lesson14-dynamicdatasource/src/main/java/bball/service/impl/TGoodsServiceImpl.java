package bball.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bball.entity.TGoods;
import bball.mapper.TGoodsMapper;
import bball.service.ITGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-13
 */
@Service
@DS("slave1")
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements ITGoodsService {
    @Override
    @DS("master")
    public void insertGoods(TGoods goods) {
        baseMapper.insert(goods);
    }

    @Override
    @DS("slave1")
    public TGoods selectSlaveGoods(Long id) {
        return baseMapper.selectById(id);
    }
}

package bball.service.impl;

import bball.annotation.ReadOnly;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import bball.entity.TGoods;
import bball.mapper.TGoodsMapper;
import bball.service.ITGoodsService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-13
 */
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements ITGoodsService {
    @Override
    public void insertGoods(TGoods goods) {
        baseMapper.insert(goods);
    }

    @Override
    @ReadOnly
    public TGoods selectSlaveGoods(Long id) {
        return baseMapper.selectById(id);
    }
}

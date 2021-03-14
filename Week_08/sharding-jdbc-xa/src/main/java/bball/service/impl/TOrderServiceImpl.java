package bball.service.impl;

import bball.entity.TOrder;
import bball.mapper.TOrderMapper;
import bball.service.ITOrderService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bball
 * @since 2021-03-14
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {
    @Override
    public List<TOrder> queryList(Wrapper<TOrder> wrapper) {
        return baseMapper.selectList(wrapper);
    }

    @Override
    public int deleteOrder(Wrapper<TOrder> wrapper) {
        return baseMapper.delete(wrapper);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void saveOrderList(List<TOrder> orderList){
        saveBatch(orderList);
    }
}

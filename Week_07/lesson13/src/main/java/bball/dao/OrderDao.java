package bball.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import bball.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<TOrder> {

}

package com.bball.lesson13.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bball.lesson13.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<TOrder> {

}

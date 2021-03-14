package com.baomidou.ant.lesson13.service.impl;

import com.baomidou.ant.lesson13.entity.TOrder;
import com.baomidou.ant.lesson13.mapper.TOrderMapper;
import com.baomidou.ant.lesson13.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

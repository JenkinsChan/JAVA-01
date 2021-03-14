package bball;

import bball.entity.TOrder;
import bball.service.ITOrderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingApplicationTest {
    @Autowired
    private ITOrderService orderService;

    @Test
    public void testInsertOrder(){
        TOrder order = TOrder.builder()
                .status("1")
                .userId(120L)
                .receiverName("张三")
                .receiverPhone("13431990911")
                .receiverAddr("广东深圳")
                .goodsId(123L)
                .paymentAmt(new BigDecimal(ThreadLocalRandom.current().nextDouble(1,10000)))
                .discountAmt(new BigDecimal(10))
                .paymentTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .dispatchTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .createTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .updateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .build();
        orderService.save(order);
    }

    @Test
    public void testUpdateOrder(){
        TOrder order = TOrder.builder().id(577969316022874113L).discountAmt(new BigDecimal(22)).build();
        orderService.updateById(order);
    }

    @Test
    public void testQueryOrder(){
        TOrder order = TOrder.builder().id(577969316022874113L).userId(123L).build();
        QueryWrapper<TOrder> queryWrapper =
                new QueryWrapper<TOrder>(order);
        System.out.println(orderService.queryList(queryWrapper));
    }

    /**
     * 测试删除订单
     */
    @Test
    public void testDeleteOrder(){
        TOrder order = TOrder.builder().id(577969316022874113L).userId(123L).build();
        QueryWrapper<TOrder> queryWrapper =
                new QueryWrapper<TOrder>(order);
        System.out.println(orderService.deleteOrder(queryWrapper));
    }

    /**
     * 测试XA事务，正确提交
     */
    @Test
    public void testCorrectXATrasaction(){
        orderService.saveOrderList(getCorrectOrderList());
    }

    /**
     * 测试XA事务，某个库的某个表报错回顾，看是否导致整个事务回滚
     */
    @Test
    public void testErrorXATrasaction(){
        List<TOrder> orderList = getCorrectOrderList();
        // 修改收件电话，让它超出数据列的宽度，是事务会不会回滚
        orderList.get(0).setReceiverPhone("12121312389993239232323232323455553434343535353653fdsfdsfsfasdffdf");
        orderService.saveOrderList(orderList);
    }


    public List<TOrder> getCorrectOrderList(){
        TOrder order1 = TOrder.builder()
                .status("1")
                .userId(120L)
                .receiverName("张三")
                .receiverPhone("13431990911")
                .receiverAddr("广东深圳")
                .goodsId(123L)
                .paymentAmt(new BigDecimal(ThreadLocalRandom.current().nextDouble(1,10000)))
                .discountAmt(new BigDecimal(10))
                .paymentTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .dispatchTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .createTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .updateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .build();

        TOrder order2 = TOrder.builder()
                .status("1")
                .userId(121L)
                .receiverName("李四")
                .receiverPhone("13431990911")
                .receiverAddr("广东深圳")
                .goodsId(123L)
                .paymentAmt(new BigDecimal(ThreadLocalRandom.current().nextDouble(1,10000)))
                .discountAmt(new BigDecimal(10))
                .paymentTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .dispatchTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .createTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .updateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .build();
        return Arrays.asList(order1,order2);
    }
}

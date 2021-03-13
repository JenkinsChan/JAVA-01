package bball;

import bball.entity.TGoods;
import bball.service.ITGoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicApplicationTest {
    @Autowired
    private ITGoodsService goodsService;

    @Test
    public void testInsertGoods(){
        TGoods goods = TGoods.builder()
                .goodsName("MacBook Pro")
                .goodsDesc("苹果笔记本")
                .cateId(123L)
                .imageId(1234L)
                .price(new BigDecimal(20000))
                .stock(100000L)
                .storeId(1123L)
                .createTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .updateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                .build();
        goodsService.insertGoods(goods);
    }

    @Test
    public void testQueryGoods(){
        TGoods goods = goodsService.selectSlaveGoods(1234L);
        System.out.println(goods);
        Assert.assertNotNull(goods);
    }

}

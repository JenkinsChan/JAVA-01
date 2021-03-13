package multidatasource;

import multidatasource.entity.Goods;
import multidatasource.service.GoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void testGetGoods(){
        Goods goods = goodsService.getGoodsById(1234L);
        System.out.println(goods);
        Assert.assertNotNull(goods);
    }

    @Test
    public void testUpdateGoods(){
        int updateNum = goodsService.updateGoodsNameById(1234L, "iphone12_plus", "苹果12plus");
        Assert.assertEquals(updateNum, 1);
    }
}

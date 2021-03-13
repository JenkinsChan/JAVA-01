package multidatasource.service;

import multidatasource.dao.GoodsDao;
import multidatasource.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    public Goods getGoodsById(Long goodsId){
        return goodsDao.getGoodsById(goodsId);
    }

    public int updateGoodsNameById(Long goodsId, String updateName,String updateDesc){
        return goodsDao.updateGoodsNameById(goodsId, updateName, updateDesc);
    }
}

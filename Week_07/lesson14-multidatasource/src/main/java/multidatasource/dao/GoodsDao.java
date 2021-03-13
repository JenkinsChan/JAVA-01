package multidatasource.dao;

import multidatasource.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDao {
    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;

    public Goods getGoodsById(Long goodsId){
        return slaveJdbcTemplate.queryForObject("select * from t_goods where id=" + goodsId, new BeanPropertyRowMapper<>(Goods.class));
    }

    public int updateGoodsNameById(Long goodsId, String updateName, String updateDesc){
        return masterJdbcTemplate.update("update t_goods set goods_name = ?, goods_desc = ?  where id = ? ",
                updateName, updateDesc, goodsId);
    }
}

package multidatasource.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *  商品实体对象
 * </p>
 *
 * @author jobob
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 图片ID
     */
    private Long imageId;

    /**
     * 店铺ID
     */
    private Long storeId;

    /**
     * 商品分类ID
     */
    private Long cateId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}

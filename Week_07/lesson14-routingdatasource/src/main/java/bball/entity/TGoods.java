package bball.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-03-13
 */
@Setter
@Getter
@Builder
public class TGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
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

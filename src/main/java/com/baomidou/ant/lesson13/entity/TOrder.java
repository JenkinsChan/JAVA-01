package com.baomidou.ant.lesson13.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bball
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 下单用户ID
     */
    private Long userId;

    /**
     * 收件人
     */
    private String receiverName;

    /**
     * 收货人联系方式
     */
    private String receiverPhone;

    /**
     * 收货地址
     */
    private String receiverAddr;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 支付金额
     */
    private BigDecimal paymentAmt;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmt;

    /**
     * 支付时间
     */
    private Long paymentTime;

    /**
     * 发货时间
     */
    private Long dispatchTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


}

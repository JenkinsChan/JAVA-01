create database ds0;
use ds0;
## 创建t_order_0 到 t_order_16
create table t_order_15(
                           id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT comment '订单编号' ,
                           status CHAR(2) NOT NULL comment '订单状态',
                           user_id BIGINT NOT NULL comment '下单用户ID',
                           receiver_name VARCHAR(128) NOT NULL comment '收件人',
                           receiver_phone VARCHAR(32) NOT NULL comment '收货人联系方式',
                           receiver_addr VARCHAR (1024) NOT NULL comment '收货地址',
                           goods_id BIGINT NOT NULL comment '商品ID',
                           payment_amt DECIMAL(38,4) NOT NULL comment '支付金额',
                           discount_amt DECIMAL(38,4) NOT NULL comment '优惠金额',
                           payment_time BIGINT NOT NULL comment '支付时间',
                           dispatch_time BIGINT NOT NULL comment '发货时间',
                           create_time BIGINT NOT NULL comment '创建时间',
                           update_time BIGINT NOT NULL comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create database ds1;
use ds1;
## 创建t_order_0 到 t_order_16
create table t_order_15(
                           id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT comment '订单编号' ,
                           status CHAR(2) NOT NULL comment '订单状态',
                           user_id BIGINT NOT NULL comment '下单用户ID',
                           receiver_name VARCHAR(128) NOT NULL comment '收件人',
                           receiver_phone VARCHAR(32) NOT NULL comment '收货人联系方式',
                           receiver_addr VARCHAR (1024) NOT NULL comment '收货地址',
                           goods_id BIGINT NOT NULL comment '商品ID',
                           payment_amt DECIMAL(38,4) NOT NULL comment '支付金额',
                           discount_amt DECIMAL(38,4) NOT NULL comment '优惠金额',
                           payment_time BIGINT NOT NULL comment '支付时间',
                           dispatch_time BIGINT NOT NULL comment '发货时间',
                           create_time BIGINT NOT NULL comment '创建时间',
                           update_time BIGINT NOT NULL comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

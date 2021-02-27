## 订单表
create table t_order(
    id BIGINT not null primary key comment '订单编号' ,
    status varchar(2) not null comment '订单状态',
    user_id BIGINT not null comment '下单用户编号',
    receiver_name varchar(128) not null comment '收件人',
    receiver_phone varchar(32) not null comment '收货人联系方式',
    receiver_addr varchar (1024) not null comment '收货地址',
    goods_id BIGINT not null comment '商品编号',
    payment_amt DECIMAL(38,4) not null comment '支付金额',
    discount_amt DECIMAL(38,4) not null comment '优惠金额',
    payment_time timestamp not null comment '支付时间',
    dispatch_time timestamp not null comment '发货时间',
    create_time timestamp not null comment '创建时间',
    update_time timestamp not null comment '更新时间'
)
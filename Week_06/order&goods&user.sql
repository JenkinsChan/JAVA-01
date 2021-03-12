## 订单表
create table t_order(
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

## 商品表
CREATE TABLE t_goods(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT comment '商品ID',
    goods_name VARCHAR(128) NOT NULL comment '商品名称',
    goods_desc VARCHAR(128) NOT NULL comment '商品描述',
    price DECIMAL(38,4) NOT NULL comment '价格',
    stock BIGINT NOT NULL comment '库存',
    image_id BIGINT NOT NULL comment '图片ID',
    store_id BIGINT NOT NULL comment '店铺ID',
    cate_id BIGINT NOT NULL comment '商品分类ID',
    create_time BIGINT NOT NULL comment '创建时间',
    update_time BIGINT NOT NULL comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

## 用户表
create table t_user(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT comment '用户ID',
   user_account VARCHAR(128) NOT NULL comment '用户账号',
   user_name VARCHAR(128) NOT NULL comment '用户名',
   user_nickname VARCHAR(128) NOT NULL comment '用户昵称',
   gender CHAR(1) NOT NULL comment '性别,0：未知（默认），1：女，2：男',
   user_pwd VARCHAR(128) NOT NULL  comment '密码',
   avatar_url VARCHAR(128) NOT NULL comment '头像地址',
   phone VARCHAR(32) NOT NULL comment '手机号码',
   credentials_type CHAR(2) NOT NULL comment '证件类型，01：身份证，02：驾驶证，03：军官证',
   credentials_id VARCHAR(128) NOT NULL comment '证件ID',
   create_time BIGINT NOT NULL comment '创建时间',
   update_time BIGINT NOT NULL comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
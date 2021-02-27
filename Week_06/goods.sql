## 商品表
create table t_goods(
    id BIGINT not null primary key comment '商品ID',
    goods_name varchar(128) not null comment '商品名称',
    goods_desc varchar(128) not null comment '商品描述',
    price DECIMAL(38,4) not null comment '价格',
    image_id BIGINT not null comment '图片ID',
    store_id BIGINT not null comment '店铺ID',
    cate_id BIGINT not null comment '商品分类ID',
    create_time timestamp not null comment '创建时间',
    update_time timestamp not null comment '更新时间'
)
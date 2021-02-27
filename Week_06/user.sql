## 用户表
create table t_user(
    id BIGINT not null primary key comment '用户ID',
    user_account varchar(128) not null  comment '用户账号',
    user_name varchar(128) not null comment '用户名',
    user_nickname varchar(128) not null comment '用户昵称',
    gender char(1) not null comment '性别,0：未知（默认），1：女，2：男',
    user_pwd varchar(128) not null  comment '密码',
    avatar_url varchar(128) not null comment '头像地址',
    phone varchar(32) not null comment '手机号码',
    credentials_type char(2) not null comment '证件类型，01：身份证，02：驾驶证，03：军官证',
    credentials_id varchar(128) not null comment '证件ID',
    create_time timestamp not null comment '创建时间',
    update_time timestamp not null comment '更新时间'
)
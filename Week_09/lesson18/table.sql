CREATE DATABASE IF NOT EXISTS hmily_exchange DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE hmily_exchange;
DROP TABLE IF EXISTS exchange;
CREATE TABLE exchange (
    id bigint NOT NULL comment '主键',
    status tinyint(4) NOT NULL comment '交易状态，1：未交易，2：交易中，3：交易失败，4：交易成功',
    offer_user_id bigint not null comment '交易发起方',
    take_user_id bigint not null comment '交易接收方',
    cny_amount decimal(20, 5) not null comment '人民币金额',
    usd_amount decimal(20, 5) not null comment '美元金额',
    initiate_currency varchar(8) not null comment '发起方的货币，cny：人民币，usd：美元',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    PRIMARY KEY (id)
) engine=InnoDB CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='货币交易表';

COMMENT ON COLUMN exchange.id IS '主键';

CREATE DATABASE IF NOT EXISTS hmily_account_a DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE hmily_account_a;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    id bigint not null AUTO_INCREMENT,
    user_id varchar(128) not null comment '用户主键',
    cny_balance decimal(20, 5) NOT NULL COMMENT '人民币余额',
    usd_balance decimal(20, 5) NOT NULL COMMENT '美元余额',
    cny_freeze_amount decimal(20, 5) NOT NULL COMMENT '人民币冻结金额，交易暂存余额',
    usd_freeze_amount decimal(20, 5) NOT NULL COMMENT '美元冻结金额，交易暂存余额',
    create_time datetime NOT NULL COMMENT '创建时间',
    update_time datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id)
) engine=InnoDB CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='用户A账户表';

create index account_user_id on `account`(user_id);

insert into `account`(id, user_id, cny_balance, usd_balance, cny_freeze_amount, usd_freeze_amount, create_time, update_time)
values (1, '10000', 10000, 70000, 0, 0, now(), now());

CREATE DATABASE IF NOT EXISTS hmily_account_b DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE hmily_account_b;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    id bigint not null AUTO_INCREMENT,
    user_id varchar(128) not null comment '用户主键',
    cny_balance decimal(20, 5) NOT NULL COMMENT '人民币余额',
    usd_balance decimal(20, 5) NOT NULL COMMENT '美元余额',
    cny_freeze_amount decimal(20, 5) NOT NULL COMMENT '人民币冻结金额，交易暂存余额',
    usd_freeze_amount decimal(20, 5) NOT NULL COMMENT '美元冻结金额，交易暂存余额',
    create_time datetime NOT NULL COMMENT '创建时间',
    update_time datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id)
) engine=InnoDB CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT='用户B账户表';
create index account_user_id on `account`(user_id);

insert into `account`(id, user_id, cny_balance, usd_balance, cny_freeze_amount, usd_freeze_amount, create_time, update_time)
values (1, '20000', 10000, 70000, 0, 0, now(), now());
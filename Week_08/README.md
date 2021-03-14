# 第8周作业
## 第15课
> 2、（必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。
并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。
>> [配置文件查看](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_08/sharding-jdbc-xa/src/main/resources/application.properties)

>> [增删改查单元测试](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_08/sharding-jdbc-xa/src/test/java/bball/ShardingApplicationTest.java)

## 第16课
> 2、（必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布
式事务应用 demo（二选一），提交到 Github。
>> 使用ShardingSphere 的 Atomikos XA 实现
>
>> [事务管理器](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_08/sharding-jdbc-xa/src/main/java/bball/config/TransactionConfiguration.java)

>> [单元测试](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_08/sharding-jdbc-xa/src/test/java/bball/ShardingApplicationTest.java)

## 经验教训
sharding-jdbc5.0的分库分表要加主键生成策略，以下两个参数：
> spring.shardingsphere.rules.sharding.key-generators.snowflake.type=SNOWFLAKE
> spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id=1

> 雪花算法16取模向右移22位：
> t_order_$->{(id >> 22) %  16}
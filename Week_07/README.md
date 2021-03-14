# 第7周作业
## 第13课
> 2、（必做）按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
>> [2.1 并发 + PrepareStatement + rewriteBatchedStatements 方式](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson13/src/main/java/bball/service/OrderService.java)

>> [2.2 并发 + mybatis-plus + rewriteBatchedStatements 方式](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson13/src/main/java/bball/service/impl/TOrderServiceImpl.java)
## 第14课
> 2、（必做）读写分离-动态切换数据源版本1.0
>> 2.1 基于 Spring/Spring Boot，配置多个数据源(例如2个，master 和 slave). 根据具体的 Service 方法是否会操作数据，注入不同的数据源,1.0版本
> 
>> [使用JdbcTemplate来选择数据源的做法](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson14-multidatasource/src/test/java/multidatasource/ApplicationTest.java)

>> [使用mybatis-plus + dynamic-datasource的做法](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson14-dynamicdatasource/src/test/java/bball/DynamicApplicationTest.java)

>> 2.2 改进一下：基于操作 AbstractRoutingDataSource 和自定义注解 readOnly 之
类的，简化自动切换数据源;支持配置多个从库；支持多个从库的负载均衡
> 
>> [AbstractRoutingDataSource + ReadOnly + 轮询算法实现](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson14-routingdatasource/src/test/java/bball/RoutingApplicationTest.java)
> 
> 3、（必做）读写分离-数据库框架版本2.0
> 
>> [ShardinSphere-JDBC + mybatis-plus](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_07/lesson14-shardingjdbc/src/test/java/bball/ShardingApplicationTest.java)
> 

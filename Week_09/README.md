# 第9周作业
## 第17课
>1、（选做）实现简单的Protocol Buffer/Thrift/gRPC(选任一个)远程调用demo。 
>> [gRPC实现方式](https://github.com/JenkinsChan/JAVA-01/tree/main/Week_09/lesson17-gRPC)

>2、（选做）实现简单的WebService-Axis2/CXF远程调用demo。 
>> [WebService-CXF远程调用](https://github.com/JenkinsChan/JAVA-01/tree/main/Week_09/lesson17-WebService)

>3、（必做）改造自定义RPC的程序，提交到github： 
>
>>1）尝试将服务端写死查找接口实现类变成泛型和反射

>>2）尝试将客户端动态代理改成AOP，添加异常处理

>>3）尝试使用Netty+HTTP作为client端传输方式
>
>[改造的项目地址](https://github.com/JenkinsChan/JAVA-01/tree/main/Week_09/lesson17-rpcfx)
>
>
>
>
## 第18课
>3、（必做）结合dubbo+hmily，实现一个TCC外汇交易处理，代码提交到github： 

>>1）用户A的美元账户和人民币账户都在A库，A使用1美元兑换7人民币；
>
>>2）用户B的美元账户和人民币账户都在B库，B使用7人民币兑换1美元；
>
>>3）设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。

>>[项目结构参考hmily的demo](https://github.com/JenkinsChan/JAVA-01/tree/main/Week_09/lesson18)

>>[表设计](https://github.com/JenkinsChan/JAVA-01/blob/main/Week_09/lesson18/table.sql)
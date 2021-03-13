package bball.aspect;

import bball.constant.DataSourceEnum;
import bball.datasource.RoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {
    private final List<DataSourceEnum> dataSourceList = Arrays.asList(DataSourceEnum.MASTER, DataSourceEnum.SLAVE1,
            DataSourceEnum.SLAVE2, DataSourceEnum.SLAVE3);
    private final AtomicInteger dataSourceCount = new AtomicInteger();

    @Pointcut("@annotation(bball.annotation.ReadOnly)")
    public void dataSourcePointCut(){}

    @Before("dataSourcePointCut()")
    public void before(JoinPoint point){
        DataSourceEnum dataSourceEnum = dataSourceList.get(incrementAndGetModulo(dataSourceList.size()));
        log.info("使用的datasource：" + dataSourceEnum);
        RoutingDataSource.setDataSource(dataSourceEnum);
    }

    @After("dataSourcePointCut()")
    public void after(JoinPoint point){
        RoutingDataSource.removeDataSource();
    }

    private int incrementAndGetModulo(int modulo){
        for(;;){
            int current = dataSourceCount.get();
            int next = (current + 1) % modulo;
            if(dataSourceCount.compareAndSet(current, next)){
                return next;
            }
        }
    }
}

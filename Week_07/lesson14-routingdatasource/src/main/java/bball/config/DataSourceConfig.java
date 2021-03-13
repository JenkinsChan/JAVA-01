package bball.config;

import bball.constant.DataSourceEnum;
import bball.datasource.RoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.slave1")
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.slave2")
    public DataSource slave2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave3")
    @ConfigurationProperties(prefix = "datasource.slave3")
    public DataSource slave3DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primary")
    @Primary
    public DataSource routingDataSource(){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.SLAVE1, slave1DataSource());
        targetDataSources.put(DataSourceEnum.SLAVE2, slave2DataSource());
        targetDataSources.put(DataSourceEnum.SLAVE3, slave3DataSource());
        return new RoutingDataSource(masterDataSource(), targetDataSources);
    }

}

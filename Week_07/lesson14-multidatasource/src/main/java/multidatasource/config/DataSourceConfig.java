package multidatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.slave1")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

}

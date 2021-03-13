package multidatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    @Primary
    public JdbcTemplate masterJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}

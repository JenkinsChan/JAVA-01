package bball.datasource;

import bball.constant.DataSourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {
    public static final ThreadLocal<DataSourceEnum> DATASOURCE_CONTEXT = new ThreadLocal<>();

    /**
     *
     * @param defaultTargetDataSource 默认的datasource
     * @param targetDataSources datasource hashmap
     */
    public RoutingDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
    }

    public static void setDataSource(DataSourceEnum dataSource){
        DATASOURCE_CONTEXT.set(dataSource);
    }

    public static void removeDataSource(){
        DATASOURCE_CONTEXT.remove();
    }

    public static DataSourceEnum getDataSource(){
        return DATASOURCE_CONTEXT.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }
}

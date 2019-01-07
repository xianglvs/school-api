package org.spring.springboot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by pure on 2018-05-06.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {

        if(DataSourceContextHolder.getDB()!=null)
            System.out.println("数据源为"+ DataSourceContextHolder.getDB());
        else
            System.out.println("使用的默认数据源"+ DataSourceContextHolder.DEFAULT_DS);
        return DataSourceContextHolder.getDB();
    }
}
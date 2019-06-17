package org.spring.springboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by pure on 2018-05-06.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Log log = LogFactory.getLog(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {

        if(DataSourceContextHolder.getDB()!=null)
            log.info("数据源为"+ DataSourceContextHolder.getDB());
        else
            log.info("使用的默认数据源"+ DataSourceContextHolder.DEFAULT_DS);
        return DataSourceContextHolder.getDB();
    }
}
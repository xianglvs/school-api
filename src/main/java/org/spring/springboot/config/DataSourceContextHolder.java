package org.spring.springboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by pure on 2018-05-06.
 */
public class DataSourceContextHolder {
    private static Log log = LogFactory.getLog(DataSourceContextHolder.class);
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "datasource1";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("切换到{"+dbType+"}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
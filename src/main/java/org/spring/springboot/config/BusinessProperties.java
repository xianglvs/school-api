package org.spring.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
@PropertySource(value = {"classpath:config/business.properties", "file:config/business.properties"}, ignoreResourceNotFound = true, encoding = "UTF-8")
public class BusinessProperties {
    private int timeout;
    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }
    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

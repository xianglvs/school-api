package org.spring.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
@PropertySource(value = {"classpath:config/business.properties", "file:config/business.properties"}, ignoreResourceNotFound = true, encoding = "UTF-8")
public class BusinessProperties {
    private String ftpUsername;
    private String ftpPassword;
    private Integer ftpPort;
    private String ftpHost;
    private String ftpEncoding;
    private String ftpClientTimeout;
    private String ftpPath;
}

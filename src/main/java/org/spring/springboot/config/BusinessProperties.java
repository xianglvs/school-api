package org.spring.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
@PropertySource(value = {"classpath:config/business.properties", "file:config/business.properties"}, ignoreResourceNotFound = true, encoding = "UTF-8")
public class BusinessProperties {
    @Value("${file.path}")
    private String filePath;
}

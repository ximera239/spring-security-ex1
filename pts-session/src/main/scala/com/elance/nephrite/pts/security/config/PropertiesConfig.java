package com.elance.nephrite.pts.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Evgeny Zhoga on 28.02.15.
 */
@Configuration
@PropertySource(value = "classpath:security.properties", ignoreResourceNotFound = false)
public class PropertiesConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

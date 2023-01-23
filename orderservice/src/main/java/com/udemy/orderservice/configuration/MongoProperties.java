package com.udemy.orderservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {

    private String uri;
    private String database;
    private String minPoolSize;
    private String maxPoolSize;

}

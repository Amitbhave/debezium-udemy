package com.udemy.loyaltyservice.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.kafka.consumer")
public class KafkaConsumerProperties {

    private String bootstrapServers;
    private Integer concurrency;
    private long backoffIntervalMillis;
    private int retryCount;
    private int maxPollIntervalMs;
    private int maxPollRecords;
    private int sessionTimeoutMs;
    private int fetchMaxBytes;

}

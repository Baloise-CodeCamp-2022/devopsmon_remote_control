package com.baloise.open.devops.monitor.admin.infrastructure.kafka;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "devopsmon.kafka")
public class KafkaConfig {
    @Value(value = "topic")
    private String topic;
}
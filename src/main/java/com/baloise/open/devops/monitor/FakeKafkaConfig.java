package com.baloise.open.devops.monitor;

import ch.basler.soba.document.SoBaDocumentEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@TestConfiguration
public class FakeKafkaConfig {

    @Bean
    @Primary
    public NewTopic topic() {
        return null;
    }

    @Bean
    @Primary
    public KafkaTemplate<String, SoBaDocumentEvent> kafkaTemplate(ProducerFactory<String, SoBaDocumentEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory) {
        };
    }
}
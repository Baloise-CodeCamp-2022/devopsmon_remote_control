package com.baloise.open.devops.monitor.admin.infrastructure.kafka;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import com.baloise.open.devops.monitor.admin.infrastructure.kafka.mapper.EventToAvroMapper;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventProducer {

    @NonNull
    private KafkaConfig config;

    @NonNull
    private final KafkaTemplate<String, com.baloise.open.devops.monitor.event.Event> kafkaTemplate;

    public void publishEvent(Event event) {
        kafkaTemplate.send(config.getTopic(), EventToAvroMapper.INSTANCE.map(event)).completable().join();
    }
}

package com.baloise.open.devops.monitor.admin.infrastructure.kafka;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventProducer {
    @Value(value = "devopsmon.kafka.topic")
    private String topic;
    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void publishEvent(EventDto record) {
        Event event = null;
        kafkaTemplate.send(topic, event).completable().join();
    }
}

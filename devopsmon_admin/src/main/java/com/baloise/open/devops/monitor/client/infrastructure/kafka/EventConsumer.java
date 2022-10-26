package com.baloise.open.devops.monitor.client.infrastructure.kafka;

import com.baloise.open.devops.monitor.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {

    @KafkaListener(id = "${devopsmon.kafka.client-id}", groupId = "${devopsmon.kafka.consumer-group}", topics = "${devopsmon.kafka.topic}")
    public void consumeEvent(ConsumerRecord<String, Event> record){
        Event event = record.value();
        log.info("Consumed event -> key={} - event={}", record.key(), event);
    }
}

package com.baloise.open.devops.monitor.controller.infrastructure.kafka;

import com.baloise.open.devops.monitor.controller.domain.service.RuleService;
import com.baloise.open.devops.monitor.controller.infrastructure.kafka.mapper.AvroToEventMapper;
import com.baloise.open.devops.monitor.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {

    private final RuleService ruleService;

    @Autowired
    public EventConsumer(RuleService ruleService){
        this.ruleService = ruleService;
    }

    @KafkaListener(id = "${devopsmon.kafka.client-id}", groupId = "${devopsmon.kafka.consumer-group}", topics = "${devopsmon.kafka.topic}")
    public void consumeEvent(ConsumerRecord<String, Event> record){
        Event event = record.value();
        ruleService.processEvent(AvroToEventMapper.INSTANCE.map(event));
        log.info("Ruled event -> key={} - event={}", record.key(), event);
    }
}

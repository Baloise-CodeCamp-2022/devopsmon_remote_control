package com.baloise.open.devops.monitor.gate.infrastructure.kafka;

import com.baloise.open.devops.monitor.gate.domain.model.Event;
import com.baloise.open.devops.monitor.gate.domain.services.EventPublisher;
import com.baloise.open.devops.monitor.gate.infrastructure.kafka.mapper.EventToAvroMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventProducer implements EventPublisher {

    @NonNull
    private KafkaConfig config;

    @NonNull
    private final KafkaTemplate<String, com.baloise.open.devops.monitor.event.Event> kafkaTemplate;

    public void publishEvent(String key, Event event) {
        com.baloise.open.devops.monitor.event.Event avroEvent = EventToAvroMapper.INSTANCE.map(event);
        kafkaTemplate.send(config.getTopic(), key, avroEvent).completable().join();
        log.info("Published event with traceId={} and UUID={}.", event.getTraceId(), event.getUuid());
    }
}

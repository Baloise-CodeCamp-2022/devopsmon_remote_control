package com.baloise.open.devops.monitor.admin.infrastructure.kafka;

import com.baloise.open.devops.monitor.admin.domain.model.Situation;
import com.baloise.open.devops.monitor.admin.domain.model.System;
import com.baloise.open.devops.monitor.admin.infrastructure.kafka.mapper.EventToAvroMapper;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.SituationDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.SystemDto;
import com.baloise.open.devops.monitor.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.AsyncResult;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class EventProducerTest {

    @Mock
    private KafkaTemplate<String, Event> kafkaTemplate;

    private EventProducer kafkaService;

    private final static String KAFKA_TOPIC = "TOPIC";

    @Mock
    private KafkaConfig kafkaConfig;

    @BeforeEach
    public void setUp() {
        kafkaService = new EventProducer(kafkaConfig,kafkaTemplate);
        when(kafkaConfig.getTopic()).thenReturn(KAFKA_TOPIC);
    }

    @Test
    public void testSend() {

        //given
        com.baloise.open.devops.monitor.admin.domain.model.Event event = com.baloise.open.devops.monitor.admin.domain.model.Event.builder()
                .uuid(UUID.randomUUID())
                .traceId("87654321")
                .situation(Situation.builder()
                        .createdAt(LocalDate.of(2020, 12, 9))
                        .initiator("ane")
                        .name("test")
                        .tags(Arrays.asList("devops", "monitor"))
                        .build())
                .affectedSystem(System.builder()
                        .instanceId("https://localhost:8091/test/affected/instance")
                        .build())
                .reportingSystem(System.builder()
                        .instanceId("https://localhost:8091/test/reporting/instance")
                        .build())
                .build();
        Event KAFKA_EVENT = EventToAvroMapper.INSTANCE.map(event);
        when(kafkaTemplate.send(eq(KAFKA_TOPIC), eq(KAFKA_EVENT))).thenReturn(new AsyncResult<>(null));
        kafkaService.publishEvent(event);
        verify(kafkaTemplate).send(eq(KAFKA_TOPIC), eq(KAFKA_EVENT));
    }
}

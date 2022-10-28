package com.baloise.open.devops.monitor.gate.infrastructure.kafka;

import com.baloise.open.devops.monitor.gate.domain.services.EventPublisher;
import com.baloise.open.devops.monitor.gate.infrastructure.kafka.mapper.EventToAvroMapper;
import com.baloise.open.devops.monitor.gate.domain.model.TestModelFactory;
import com.baloise.open.devops.monitor.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventProducerTest {

    @Mock
    private KafkaTemplate<String, Event> kafkaTemplate;

    private EventPublisher kafkaService;

    private final static String KAFKA_TOPIC = "TOPIC";

    @Mock
    private KafkaConfig kafkaConfig;

    @BeforeEach
    public void setUp() {
        kafkaService = new EventProducer(kafkaConfig, kafkaTemplate);
        when(kafkaConfig.getTopic()).thenReturn(KAFKA_TOPIC);
    }

    @Test
    public void testSend() {

        //given
        com.baloise.open.devops.monitor.gate.domain.model.Event event = TestModelFactory.createDefault();
        Event KAFKA_EVENT = EventToAvroMapper.INSTANCE.map(event);
        when(kafkaTemplate.send(eq(KAFKA_TOPIC), any(), eq(KAFKA_EVENT))).thenReturn(new AsyncResult<>(null));
        kafkaService.publishEvent(UUID.randomUUID().toString(), event);
        verify(kafkaTemplate).send(eq(KAFKA_TOPIC), any(), eq(KAFKA_EVENT));
    }
}

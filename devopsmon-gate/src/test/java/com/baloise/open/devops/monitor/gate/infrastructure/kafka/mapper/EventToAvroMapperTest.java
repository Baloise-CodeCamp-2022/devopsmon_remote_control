package com.baloise.open.devops.monitor.gate.infrastructure.kafka.mapper;

import com.baloise.open.devops.monitor.gate.domain.model.Event;
import com.baloise.open.devops.monitor.gate.domain.model.TestModelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class EventToAvroMapperTest {

    @Test
    public void givenEventDto_whenMapped_thenModel() {
        //given
        Event event = TestModelFactory.createDefault();

        //when
        com.baloise.open.devops.monitor.event.Event avroEvent = EventToAvroMapper.INSTANCE.map(event);

        //then
        Assertions.assertNotNull(avroEvent.getUuid());
        Assertions.assertEquals("87654321", avroEvent.getTraceId());

        com.baloise.open.devops.monitor.event.Situation situation = avroEvent.getSituation();
        Assertions.assertEquals("test", situation.getName());
        Assertions.assertEquals("ane", situation.getInitiator());
        Assertions.assertEquals(LocalDateTime.of(2020, 12, 9, 11, 23, 34), situation.getCreatedAt());
        Assertions.assertEquals(Arrays.asList("devops", "monitor"), situation.getTags());

        com.baloise.open.devops.monitor.event.System affectedSystem = avroEvent.getAffectedSystem();
        Assertions.assertEquals("https://localhost:8091/test/affected/instance", affectedSystem.getInstanceId());

        com.baloise.open.devops.monitor.event.System reportingSystem = avroEvent.getReportingSystem();
        Assertions.assertEquals("https://localhost:8091/test/reporting/instance", reportingSystem.getInstanceId());
    }
}

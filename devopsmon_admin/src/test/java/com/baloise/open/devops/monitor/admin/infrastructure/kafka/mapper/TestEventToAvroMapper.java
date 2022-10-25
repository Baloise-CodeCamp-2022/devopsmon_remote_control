package com.baloise.open.devops.monitor.admin.infrastructure.kafka.mapper;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import com.baloise.open.devops.monitor.admin.domain.model.Situation;
import com.baloise.open.devops.monitor.admin.domain.model.System;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class TestEventToAvroMapper {

    @Test
    public void givenEventDto_whenMapped_thenModel() {
        //given
        Event event = Event.builder()
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
                .specific("{}")
                .build();

        //when
        com.baloise.open.devops.monitor.event.Event avroEvent = EventToAvroMapper.INSTANCE.map(event);

        //then
        Assertions.assertNotNull(avroEvent.getUuid());
        Assertions.assertEquals("87654321", avroEvent.getTraceId());

        com.baloise.open.devops.monitor.event.Situation situation = avroEvent.getSituation();
        Assertions.assertEquals("test", situation.getName());
        Assertions.assertEquals("ane", situation.getInitiator());
        Assertions.assertEquals(LocalDate.of(2020, 12, 9), situation.getCreatedAt());
        Assertions.assertEquals(Arrays.asList("devops", "monitor"), situation.getTags());

        com.baloise.open.devops.monitor.event.System affectedSystem = avroEvent.getAffectedSystem();
        Assertions.assertEquals("https://localhost:8091/test/affected/instance", affectedSystem.getInstanceId());

        com.baloise.open.devops.monitor.event.System reportingSystem = avroEvent.getReportingSystem();
        Assertions.assertEquals("https://localhost:8091/test/reporting/instance", reportingSystem.getInstanceId());
    }
}

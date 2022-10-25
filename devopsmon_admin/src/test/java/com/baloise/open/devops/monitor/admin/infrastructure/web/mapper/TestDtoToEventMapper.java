package com.baloise.open.devops.monitor.admin.infrastructure.web.mapper;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import com.baloise.open.devops.monitor.admin.domain.model.Situation;
import com.baloise.open.devops.monitor.admin.domain.model.System;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.SystemDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.SituationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class TestDtoToEventMapper {

    @Test
    public void givenEventDto_whenMapped_thenModel() {
        //given
        EventDto eventDto = EventDto.builder()
                .id(UUID.randomUUID())
                .traceId("87654321")
                .situation(SituationDto.builder()
                        .createdAt(LocalDate.of(2020, 12, 9))
                        .initiator("ane")
                        .name("test")
                        .tags(Arrays.asList("devops", "monitor"))
                        .build())
                .affectedSystem(SystemDto.builder()
                        .instanceId("https://localhost:8091/test/affected/instance")
                        .build())
                .reportingSystem(SystemDto.builder()
                        .instanceId("https://localhost:8091/test/reporting/instance")
                        .build())
                .build();

        //when
        Event eventModel = DtoToEventMapper.INSTANCE.map(eventDto);

        //then
        Assertions.assertNotNull(eventModel.getId());
        Assertions.assertEquals("87654321", eventModel.getTraceId());

        Situation situation = eventModel.getSituation();
        Assertions.assertEquals("test", situation.getName());
        Assertions.assertEquals("ane", situation.getInitiator());
        Assertions.assertEquals(LocalDate.of(2020, 12, 9), situation.getCreatedAt());
        Assertions.assertEquals(Arrays.asList("devops", "monitor"), situation.getTags());

        System affectedSystem = eventModel.getAffectedSystem();
        Assertions.assertEquals("https://localhost:8091/test/affected/instance", affectedSystem.getInstanceId());

        System reportingSystem = eventModel.getReportingSystem();
        Assertions.assertEquals("https://localhost:8091/test/reporting/instance", reportingSystem.getInstanceId());
    }
}

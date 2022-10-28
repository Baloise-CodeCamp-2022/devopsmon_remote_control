package com.baloise.open.devops.monitor.gate.infrastructure.web.mapper;

import com.baloise.open.devops.monitor.gate.domain.model.Event;
import com.baloise.open.devops.monitor.gate.domain.model.Situation;
import com.baloise.open.devops.monitor.gate.domain.model.System;
import com.baloise.open.devops.monitor.gate.infrastructure.web.TestModelDtoFactory;
import com.baloise.open.devops.monitor.gate.infrastructure.web.model.EventDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DtoToEventMapperTest {

    @Test
    public void givenEventDto_whenMapped_thenModel() {
        //given
        EventDto eventDto = TestModelDtoFactory.createDefault();

        //when
        Event eventModel = DtoToEventMapper.INSTANCE.map(eventDto);

        //then
        Assertions.assertNotNull(eventModel.getUuid());
        Assertions.assertEquals("87654321", eventModel.getTraceId());

        Situation situation = eventModel.getSituation();
        Assertions.assertEquals("test", situation.getName());
        Assertions.assertEquals("ane", situation.getInitiator());
        Assertions.assertEquals(LocalDateTime.of(2020, 12, 9, 11, 23, 34), situation.getCreatedAt());
        Assertions.assertEquals(Arrays.asList("devops", "monitor"), situation.getTags());

        System affectedSystem = eventModel.getAffectedSystem();
        Assertions.assertEquals("https://localhost:8091/test/affected/instance", affectedSystem.getInstanceId());

        System reportingSystem = eventModel.getReportingSystem();
        Assertions.assertEquals("https://localhost:8091/test/reporting/instance", reportingSystem.getInstanceId());
    }
}

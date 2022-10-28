package com.baloise.open.devops.monitor.gate.infrastructure.web;

import com.baloise.open.devops.monitor.gate.infrastructure.web.mapper.EventToDtoMapper;
import com.baloise.open.devops.monitor.gate.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.gate.domain.model.TestModelFactory;

public class TestModelDtoFactory {

    public static EventDto createDefault() {
        return EventToDtoMapper.INSTANCE.map(TestModelFactory.createDefault());
    }
}

package com.baloise.open.devops.monitor.client.infrastructure.web;

import com.baloise.open.devops.monitor.client.infrastructure.web.mapper.EventToDtoMapper;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.client.domain.model.TestModelFactory;

public class TestModelDtoFactory {

    public static EventDto createDefault() {
        return EventToDtoMapper.INSTANCE.map(TestModelFactory.createDefault());
    }
}

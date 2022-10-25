package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.infrastructure.web.mapper.EventToDtoMapper;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.client.domain.model.TestModelFactory;

public class TestModelDtoFactory {

    public static EventDto createDefault() {
        return EventToDtoMapper.INSTANCE.map(TestModelFactory.createDefault());
    }
}

package com.baloise.open.devops.monitor.admin.infrastructure.kafka.mapper;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import org.mapstruct.factory.Mappers;

public interface EventToAvroMapper {
    EventToAvroMapper INSTANCE = Mappers.getMapper(EventToAvroMapper.class);

    com.baloise.open.devops.monitor.event.Event map(Event event);
}

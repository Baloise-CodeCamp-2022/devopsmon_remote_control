package com.baloise.open.devops.monitor.client.infrastructure.kafka.mapper;

import com.baloise.open.devops.monitor.client.domain.model.Event;
import com.baloise.open.devops.monitor.client.domain.model.System;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface EventToAvroMapper {
    EventToAvroMapper INSTANCE = Mappers.getMapper(EventToAvroMapper.class);

    com.baloise.open.devops.monitor.event.Event map(Event event);

    com.baloise.open.devops.monitor.event.System map(System system);

    default CharSequence map(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return uuid.toString();
    }
}

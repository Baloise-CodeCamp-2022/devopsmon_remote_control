package com.baloise.open.devops.monitor.gate.infrastructure.web.mapper;

import com.baloise.open.devops.monitor.gate.domain.model.Event;
import com.baloise.open.devops.monitor.gate.infrastructure.web.model.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToEventMapper {
    DtoToEventMapper INSTANCE = Mappers.getMapper(DtoToEventMapper.class);
    Event map(EventDto event);
}

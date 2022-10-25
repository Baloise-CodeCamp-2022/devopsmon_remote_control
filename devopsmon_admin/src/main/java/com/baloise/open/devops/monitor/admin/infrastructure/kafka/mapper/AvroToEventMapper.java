package com.baloise.open.devops.monitor.admin.infrastructure.kafka.mapper;

import com.baloise.open.devops.monitor.admin.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvroToEventMapper {
    AvroToEventMapper INSTANCE = Mappers.getMapper( AvroToEventMapper.class );

    Event map(com.baloise.open.devops.monitor.event.Event event);

    default String map(CharSequence charSequence){
        return String.valueOf(charSequence);
    }
}

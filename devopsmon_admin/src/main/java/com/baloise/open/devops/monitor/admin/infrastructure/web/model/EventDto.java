package com.baloise.open.devops.monitor.admin.infrastructure.web.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class EventDto {
    private UUID uuid;
    private String traceId;
    private SituationDto situation;
    private SystemDto affectedSystem;
    private SystemDto reportingSystem;
    private String specific;
}

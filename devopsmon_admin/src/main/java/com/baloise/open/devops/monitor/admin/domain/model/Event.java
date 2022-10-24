package com.baloise.open.devops.monitor.admin.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder()
public class Event {
    private UUID id;
    private String traceId;
}
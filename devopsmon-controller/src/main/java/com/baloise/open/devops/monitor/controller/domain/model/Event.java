package com.baloise.open.devops.monitor.controller.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder()
public class Event {
    private UUID uuid;
    private String traceId;
    private Situation situation;
    private System affectedSystem;
    private System reportingSystem;
    private String specific;
}

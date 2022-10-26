package com.baloise.open.devops.monitor.client.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Situation {
    private String name;
    private String initiator;
    private LocalDateTime createdAt;
    private List<String> tags;
}

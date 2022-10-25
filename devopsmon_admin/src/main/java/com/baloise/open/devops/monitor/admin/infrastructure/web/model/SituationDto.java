package com.baloise.open.devops.monitor.admin.infrastructure.web.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class SituationDto {
    private String name;
    private String initiator;
    private LocalDate createdAt;
    private List<String> tags;
}

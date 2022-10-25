package com.baloise.open.devops.monitor.admin.infrastructure.web.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class SystemDto {
    private String instanceId;
    private String location;
}

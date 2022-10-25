package com.baloise.open.devops.monitor.admin.infrastructure.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemDto {

    @Schema(description = "ID leading to the instance that is affected or reporting the event, most likely a URL.",
    required = true,
    example = "https://localhost:8091/test/instance")
    private String instanceId;
}

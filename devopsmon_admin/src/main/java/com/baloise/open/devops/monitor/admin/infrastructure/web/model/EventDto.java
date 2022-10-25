package com.baloise.open.devops.monitor.admin.infrastructure.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Generic description of a DevOps event used to control the environment.")
public class EventDto {

    @Schema(description = "Unique ID that is used to identify an event across all platforms.",
            pattern = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$",
            required = true,
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID uuid;

    @Schema(description = "Unique ID across several events in order to keep them together, such as a CID or trace ID.",
            example = "6543210987654321")
    private String traceId;

    @Schema(description = "Provides a more detailed description of the situation.",
            required = true)
    private SituationDto situation;

    @Schema(description = "Provides detailed information on the system that was affected by the event.",
            required = true)
    private SystemDto affectedSystem;

    @Schema(description = "Provides detailed information on the system that was reporting by the event. This can be different from the affected system, i.e. a monitor.")
    private SystemDto reportingSystem;

    @Schema(description = "JSON string that is used to provide extra, specific data describing that event, that can be interpreted by dedicated systems or services.")
    private String specific;
}

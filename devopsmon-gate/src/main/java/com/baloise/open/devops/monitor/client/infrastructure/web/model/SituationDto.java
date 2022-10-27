package com.baloise.open.devops.monitor.client.infrastructure.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituationDto {
    @Schema(description = "Name describing the event sufficiently.",
            example ="Service started",
            required = true)
    private String name;

    @Schema(description = "Identifier describing the user or system/ service sufficiently, that caused this event.",
            example ="GWR monitor sidecar",
            required = true)
    private String initiator;

    @Schema(description = "Timestamp when the event was created.",
            example ="Service started",
            required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Schema(description = "Collection of tags that can be used to describe teh event in more detail, such as GWR, error, etc.",
            example ="[\"life-cycle\", \"GWR\"]")
    private List<String> tags;
}

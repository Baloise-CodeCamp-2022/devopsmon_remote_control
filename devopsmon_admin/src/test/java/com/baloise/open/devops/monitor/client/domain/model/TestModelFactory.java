package com.baloise.open.devops.monitor.client.domain.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class TestModelFactory {

    public static Event createDefault() {
        return Event.builder()
                .uuid(UUID.randomUUID())
                .traceId("87654321")
                .situation(Situation.builder()
                        .createdAt(LocalDateTime.of(2020, 12, 9, 11, 23, 34))
                        .initiator("ane")
                        .name("test")
                        .tags(Arrays.asList("devops", "monitor"))
                        .build())
                .affectedSystem(System.builder()
                        .instanceId("https://localhost:8091/test/affected/instance")
                        .build())
                .reportingSystem(System.builder()
                        .instanceId("https://localhost:8091/test/reporting/instance")
                        .build())
                .build();
    }
}

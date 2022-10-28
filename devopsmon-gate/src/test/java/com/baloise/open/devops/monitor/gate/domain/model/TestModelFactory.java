package com.baloise.open.devops.monitor.gate.domain.model;

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
    public static Event createDefault(String name,String traceId,String initiator,String instanceId,String... tags) {
        return Event.builder()
                .uuid(UUID.randomUUID())
                .traceId(traceId)
                .situation(Situation.builder()
                        .createdAt(LocalDateTime.now())
                        .initiator(initiator)
                        .name(name)
                        .tags(Arrays.asList(tags))
                        .build())
                .affectedSystem(System.builder()
                        .instanceId(instanceId)
                        .build())
                .reportingSystem(System.builder()
                        .instanceId(instanceId)
                        .build())
                .build();
    }
}

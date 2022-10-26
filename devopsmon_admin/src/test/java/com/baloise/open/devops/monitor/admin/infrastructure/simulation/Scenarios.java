package com.baloise.open.devops.monitor.admin.infrastructure.simulation;

import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.SituationDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.SystemDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Scenarios {
    public static Scenario getServerRunScenario() {
        String reporterId = "";
        Scenario scenario = new Scenario()
                .withStep(createServerDto(reporterId, "startServer", null, "infrastructure"));

        for (int i = 10; i < 999; i++) {
            int stepIncrement = i / 10;
            int bound = stepIncrement + (int) (stepIncrement * 1.4);
            EventDto memoryEvent = createServerDto(reporterId, "metric", String.valueOf(ThreadLocalRandom.current().nextInt(stepIncrement, bound)), "infrastructure", "metric", "memory");
            scenario.withStep(memoryEvent);
            double by = Math.abs(Math.sin(i)) * 100;
            EventDto cpuEvent = createServerDto(reporterId, "metric", String.valueOf(by), "infrastructure", "metric", "cpu");
            scenario.withStep(cpuEvent);
        }
        scenario.withStep(createServerDto(reporterId, "stopServer", null, "infrastructure"));

        return scenario;
    }

    static Supplier<EventDto> memoryEventSupplier = () -> createServerDto("reporter", "metric", String.valueOf(ThreadLocalRandom.current().nextInt(20, 80)), "infrastructure", "metric", "memory");

    public static <T, R> Supplier<R> bind(Function<T, R> fn, T val) {
        return () -> fn.apply(val);
    }

    public static Stream<EventDto> getInfiniteServerStream() {
        Stream<EventDto> infiniteStream = Stream.generate(memoryEventSupplier);
        return infiniteStream;
    }

    private static EventDto createServerDto(String reporterId, String name, String specific, String... tag) {
        return EventDto.builder()
                .uuid(UUID.randomUUID())
                .traceId("trace-id" + UUID.randomUUID())
                .situation(SituationDto.builder()
                        .name(name)
                        .tags(Arrays.asList(tag))
                        .createdAt(LocalDateTime.now())
                        .initiator("initiator").build())
                .affectedSystem(SystemDto.builder()
                        .instanceId("affected_instanceID")
                        .build())
                .reportingSystem(SystemDto.builder()
                        .instanceId(reporterId)
                        .build())
                .specific(specific)
                .build();
    }
}

package com.baloise.open.devops.monitor.client.infrastructure.web.simulation.scenario;

import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.SituationDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.SystemDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.simulation.Scenario;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Scenarios {
    public static Scenario getServerRunScenario() {
        String reporterId = "";
        Scenario scenario = new Scenario()
                .withStep(createServerDto(reporterId, "startServer", null, null, "infrastructure"));

        for (int i = 10; i < 999; i++) {
            int stepIncrement = i / 10;
            int bound = stepIncrement + (int) (stepIncrement * 1.4);
            EventDto memoryEvent = createServerDto(reporterId, "metric", null, String.valueOf(ThreadLocalRandom.current().nextInt(stepIncrement, bound)), "infrastructure", "metric", "memory");
            scenario.withStep(memoryEvent);
            double by = Math.abs(Math.sin(i)) * 100;
            EventDto cpuEvent = createServerDto(reporterId, "metric", null, String.valueOf(by), "infrastructure", "metric", "cpu");
            scenario.withStep(cpuEvent);
        }
        scenario.withStep(createServerDto(reporterId, "stopServer", null, null, "infrastructure"));

        return scenario;
    }

    static Supplier<EventDto> memoryEventSupplier = () -> createServerDto("reporter", "metric", null, String.valueOf(ThreadLocalRandom.current().nextInt(20, 80)), "infrastructure", "metric", "memory");

    private static EventDto createServerDto(String reporterId, String name, String traceId, String specific, String... tags) {
        return EventDto.builder()
                .uuid(UUID.randomUUID())
                .traceId(traceId)
                .situation(SituationDto.builder()
                        .name(name)
                        .tags(Arrays.asList(tags))
                        .createdAt(LocalDateTime.now())
                        .initiator("GWR Sidecar").build())
                .affectedSystem(SystemDto.builder()
                        .instanceId("https://int-target.balgroupit.com/pc/PolicyCenter.do")
                        .build())
                .reportingSystem(SystemDto.builder()
                        .instanceId(reporterId)
                        .build())
                .specific(specific)
                .build();
    }
}

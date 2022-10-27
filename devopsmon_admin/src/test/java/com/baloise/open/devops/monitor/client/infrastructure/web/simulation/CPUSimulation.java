package com.baloise.open.devops.monitor.client.infrastructure.web.simulation;

import com.baloise.open.devops.monitor.client.domain.model.TestModelFactory;
import com.baloise.open.devops.monitor.client.infrastructure.web.mapper.EventToDtoMapper;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.client.infrastructure.web.simulation.scenario.HomogeneousScenario;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public class CPUSimulation {
    public static HomogeneousScenario getCpuSimulation() {
        Function<Integer, EventDto> stepGenerator = (index) -> {
            EventDto eventDto=  EventToDtoMapper.INSTANCE.map(TestModelFactory.createDefault("CPU", "trace_id", "initiator", "https://localhost:8091/test/reporting/instance", "infrastructure", "metrics")          );
            eventDto.setSpecific(String.valueOf(index));
            return eventDto;
        };
        return new HomogeneousScenario()
                .withStepCount(100)
                .withStepGenerator(stepGenerator)
                .withDuration(Duration.of(2, ChronoUnit.SECONDS));
    }
}

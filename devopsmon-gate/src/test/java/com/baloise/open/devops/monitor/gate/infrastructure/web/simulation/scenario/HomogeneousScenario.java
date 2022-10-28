package com.baloise.open.devops.monitor.gate.infrastructure.web.simulation.scenario;

import com.baloise.open.devops.monitor.gate.infrastructure.web.model.EventDto;
import lombok.Getter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
public class HomogeneousScenario<T extends HomogeneousScenario<T>> extends Scenario<T> {

    private int steps = 1;
    private Duration duration = Duration.of(1, ChronoUnit.SECONDS);
    private Function<Integer, EventDto> stepGenerator;


    public T withStepGenerator(Function<Integer, EventDto> stepGenerator) {
        this.stepGenerator = stepGenerator;
        return self();
    }


    public T withDuration(Duration duration) {
        this.duration = duration;
        return self();
    }


    public T withStepCount(int count) {
        steps = count;
        return self();
    }

    private T self() {
        return (T) this;
    }

    public void process(Consumer<EventDto> consume) {
        for (int i = 0; i < steps; i++) {
            EventDto event = stepGenerator.apply(i);
            consume.accept(event);
            if (duration != null) {
                try {
                    Thread.sleep(duration.toMillis());
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

package com.baloise.open.devops.monitor.client.infrastructure.web.simulation;

import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
public class Scenario extends ArrayList<EventDto> {
    public Scenario withStep(EventDto step) {
        add(step);
        return this;
    }

    public Scenario withSteps(Collection<EventDto> steps) {
        addAll(steps);
        return this;
    }
}

package com.baloise.open.devops.monitor.gate.infrastructure.web.simulation.scenario;

import com.baloise.open.devops.monitor.gate.infrastructure.web.model.EventDto;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Scenario<T extends Scenario<T>> {
    protected List<EventDto> events = new ArrayList();

    public T withStep(EventDto step) {
        events.add(step);
        return self();
    }
    private T self(){
        return (T)this;
    }


}

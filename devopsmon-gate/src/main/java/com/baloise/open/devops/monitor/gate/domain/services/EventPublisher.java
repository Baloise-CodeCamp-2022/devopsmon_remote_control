package com.baloise.open.devops.monitor.gate.domain.services;

import com.baloise.open.devops.monitor.gate.domain.model.Event;

public interface EventPublisher {
    void publishEvent(String key, Event event);
}

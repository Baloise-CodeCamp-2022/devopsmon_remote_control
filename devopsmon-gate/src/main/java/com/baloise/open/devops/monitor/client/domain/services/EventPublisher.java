package com.baloise.open.devops.monitor.client.domain.services;

import com.baloise.open.devops.monitor.client.domain.model.Event;

public interface EventPublisher {
    void publishEvent(String key, Event event);
}

package com.baloise.open.devops.monitor.admin.domain.services;

import com.baloise.open.devops.monitor.admin.domain.model.Event;

public interface EventPublisher {
    void publishEvent(String key, Event event);
}

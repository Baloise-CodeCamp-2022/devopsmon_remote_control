package com.baloise.open.devops.monitor.controller.domain.service;

import com.baloise.open.devops.monitor.controller.domain.model.Event;

public interface RuleService {
    void processEvent(Event event);
}

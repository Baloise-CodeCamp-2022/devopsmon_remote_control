package com.baloise.open.devops.monitor.controller.domain.service;

import com.baloise.open.devops.monitor.controller.domain.model.RecommendedAction;

public interface ActionService {
    void proposeRecommendedAction(RecommendedAction action);
}

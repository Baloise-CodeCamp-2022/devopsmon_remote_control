package com.baloise.open.devops.monitor.controller.infrastructure.kafka;

import com.baloise.open.devops.monitor.controller.domain.model.RecommendedAction;
import com.baloise.open.devops.monitor.controller.domain.service.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecommendedActionService implements ActionService {
    @Override
    public void proposeRecommendedAction(RecommendedAction action) {
        log.info("Recommending action: {}", action);
    }
}

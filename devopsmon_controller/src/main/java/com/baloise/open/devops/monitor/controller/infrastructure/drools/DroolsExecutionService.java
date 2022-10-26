package com.baloise.open.devops.monitor.controller.infrastructure.drools;

import com.baloise.open.devops.monitor.controller.domain.model.Event;
import com.baloise.open.devops.monitor.controller.domain.service.RuleService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroolsExecutionService implements RuleService {
    private final KieContainer kieContainer;

    @Autowired
    public DroolsExecutionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void processEvent(Event event) {
        KieSession kieSession = kieContainer.getKieBase().newKieSession();
        kieSession.setGlobal("log", new LogFactory());
        kieSession.insert(event);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

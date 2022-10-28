package com.baloise.open.devops.monitor.controller.infrastructure.drools;

import com.baloise.open.devops.monitor.controller.domain.model.Event;
import com.baloise.open.devops.monitor.controller.domain.service.RuleService;
import com.baloise.open.devops.monitor.controller.infrastructure.drools.mapper.SpecificMapper;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroolsExecutionService implements RuleService {
    private final KieBase kieBase;

    private final SpecificMapper mapper;

    @Autowired
    public DroolsExecutionService(KieBase kieBase, SpecificMapper mapper) {
        this.kieBase = kieBase;
        this.mapper = mapper;
    }

    public void processEvent(Event event) {
        KieSession kieSession = kieBase.newKieSession();

        kieSession.setGlobal("log", new LogFactory());
        kieSession.setGlobal("mapper", mapper);

        kieSession.insert(event);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

package com.baloise.open.devops.monitor.controller.infrastructure.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {
    @Bean
    public KieBase kieContainer() {
        return createByFileSystem();
    }

    private KieBase createByFileSystem() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/cpu.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/event.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/guidewire.drl"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
        kieBaseConfiguration.setOption(MultithreadEvaluationOption.YES);

        return kieServices.newKieContainer(kieModule.getReleaseId()).newKieBase(kieBaseConfiguration);
    }
}

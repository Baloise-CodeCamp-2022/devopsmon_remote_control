package com.baloise.open.devops.monitor.controller.infrastructure.drools.mapper;

import com.baloise.open.devops.monitor.controller.infrastructure.drools.model.CpuSaturation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class SpecificMapper {
    private ObjectMapper objectMapper;

    @PostConstruct
    public void initMapper() {
        objectMapper = new ObjectMapper();
    }

    public CpuSaturation getCpuSaturation(String inJsonValue) {
        try {
            return objectMapper.readValue(inJsonValue, CpuSaturation.class);
        } catch (JsonProcessingException jsonProblem) {
            log.error("Problem mapping specific value for CPU saturation: {}", jsonProblem);
            return null;
        }
    }
}

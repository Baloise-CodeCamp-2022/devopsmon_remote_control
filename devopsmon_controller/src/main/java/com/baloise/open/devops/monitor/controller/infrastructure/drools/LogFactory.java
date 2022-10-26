package com.baloise.open.devops.monitor.controller.infrastructure.drools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFactory {
    public void log(String message, Object... args) {
        log.info(message, args);
    }
}

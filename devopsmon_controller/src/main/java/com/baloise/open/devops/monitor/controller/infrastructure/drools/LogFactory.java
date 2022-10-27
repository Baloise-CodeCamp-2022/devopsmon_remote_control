package com.baloise.open.devops.monitor.controller.infrastructure.drools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFactory {
    public void info(String message, Object... args) {
        log.info(message, args);
    }

    public void warn(String message, Object... args) {
        log.warn(message, args);
    }

    public void error(String message, Object... args) {
        log.error(message, args);
    }
}

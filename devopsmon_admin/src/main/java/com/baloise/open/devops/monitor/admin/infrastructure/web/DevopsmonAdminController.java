package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DevopsmonAdminController {

    @PostMapping("/create")
    public void createEvent(@RequestBody EventDto event){
        log.info("Created event with traceId={} and UUID={}.", event.getTraceId(), event.getId());
    }
}

package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.openapi.DevopsmonAdminApi;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DevopsmonAdminController implements DevopsmonAdminApi {

    @PostMapping("/create")
    @Timed(value = "greeting.time", description = "Time taken to return greeting",
        percentiles = {0.5, 0.90})
    public void createEvent(@RequestBody EventDto event){
        log.info("Created event with traceId={} and UUID={}.", event.getTraceId(), event.getId());
    }
    @GetMapping("/blubb")
    @Timed(value = "greeting.time", description = "Time taken to return greeting",
        percentiles = {0.5, 0.90})
    public void createBlubb(){
        log.info("Created event with traceId={} and UUID={}.");
    }
}

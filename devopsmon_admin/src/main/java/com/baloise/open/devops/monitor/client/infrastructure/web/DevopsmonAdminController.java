package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.openapi.DevopsmonAdminApi;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DevopsmonAdminController implements DevopsmonAdminApi {

    private static AtomicInteger counter = new AtomicInteger(0);

    @PostMapping("/create")
    @Timed(value = "greeting.time", description = "Time taken to return greeting",
        percentiles = {0.5, 0.90})
    public void createEvent(@RequestBody EventDto event){
        log.info("Created event with traceId={} and UUID={}.", event.getTraceId(), event.getUuid());
    }
    @GetMapping("/blubb")
    @Timed(value = "greeting.time", description = "Time taken to return greeting",
        percentiles = {0.5, 0.90})
    public void createBlubb() throws InterruptedException {
        int count = counter.getAndIncrement();
        if (count > 7 && count < 15) {
            log.info("delay as count > 7");
            Thread.sleep(2000 + count * 100);
        } else {
            Thread.sleep(1000);
        }
        log.info("Created event with traceId={} and UUID={}.");
    }
}

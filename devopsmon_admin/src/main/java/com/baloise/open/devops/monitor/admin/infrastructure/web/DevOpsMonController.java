package com.baloise.open.devops.monitor.admin.infrastructure.web;

import com.baloise.open.devops.monitor.admin.domain.services.EventPublisher;
import com.baloise.open.devops.monitor.admin.infrastructure.web.mapper.DtoToEventMapper;
import com.baloise.open.devops.monitor.admin.infrastructure.web.model.EventDto;
import com.baloise.open.devops.monitor.admin.infrastructure.web.openapi.DevopsmonAdminApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/event")
@Slf4j
public class DevOpsMonController implements DevopsmonAdminApi {

    private final EventPublisher publisher;

    @Autowired
    public DevOpsMonController(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/create")
    public void createEvent(@RequestBody EventDto event) {
        publisher.publishEvent(UUID.randomUUID().toString(), DtoToEventMapper.INSTANCE.map(event));
    }
}

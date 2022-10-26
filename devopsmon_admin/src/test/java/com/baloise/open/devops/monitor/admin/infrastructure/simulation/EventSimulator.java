package com.baloise.open.devops.monitor.admin.infrastructure.simulation;

import com.baloise.open.devops.monitor.client.infrastructure.web.DevOpsMonController;
import com.baloise.open.devops.monitor.client.infrastructure.web.model.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Disabled
public class EventSimulator {
    String endpoint = "http://localhost:8080/event/create";
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    private CloseableHttpClient client = HttpClients.createDefault();
    @Autowired
    DevOpsMonController controller;

    @Test
    @Disabled
    public void simulate() throws IOException {
        simulate(Scenarios.getServerRunScenario());
        //simulate(Scenarios.getInfiniteServerStream());
    }

    public void simulate(List<EventDto> scenario) {
        scenario.forEach(this::send);
    }

    public void simulate(Stream<EventDto> scenario) {
        scenario.forEach(this::send);
    }

    private void send(EventDto event) {
        try {
            HttpPost postCall = new HttpPost(endpoint);
            postCall.setEntity(new StringEntity(mapper.writeValueAsString(event)));
            postCall.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(postCall);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

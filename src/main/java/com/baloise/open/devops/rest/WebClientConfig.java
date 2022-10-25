package com.baloise.open.devops.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    /**
     * The WebClient needs to be defined as a bean, so Sleuth can inject the tracing headers.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
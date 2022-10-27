package com.baloise.open.devops.monitor.client.infrastructure.web;

import com.baloise.open.devops.monitor.client.infrastructure.web.openapi.DevopsmonAdminApi;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DevopsmonAdminController implements DevopsmonAdminApi {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static AtomicInteger cpu = new AtomicInteger(5);

  public DevopsmonAdminController() {
    registerCpuGauge();
  }

  @PostMapping("/create")
  public void createEvent(@RequestBody String event) {
    log.info("received alert from alertmanager: {}.", event);
  }

  @GetMapping("/service")
  @Timed(value = "greeting.time", description = "Time taken to return greeting",
      percentiles = {0.5, 0.90})
  public void consumeService() throws InterruptedException {
    Thread.sleep(getCpuUsageBasedDelay());
    log.info("Consumed service {} times.", counter.incrementAndGet());
  }

  long getCpuUsageBasedDelay() {
    return cpu.get() * 100L;
  }

  @GetMapping("/cpu/{usage}")
  public void setCpuUsage(@PathVariable("usage") int usage) {
    if (usage >= 0 && usage <= 100) {
      cpu.set(usage);
      log.info("Set usage to {}.", usage);
      return;
    }
    log.warn("Usage must be between 0 and 100.");
  }

  private void registerCpuGauge() {
    Gauge.builder("custom_fake_cpu_usage", () -> cpu.get())
        .strongReference(true)
        .register(Metrics.globalRegistry);
  }

  static boolean isIncreasedDelayRequired(int count) {
    return count % 5 == 0 || count % 6 == 0 || count % 7 == 0 || count % 8 == 0;
  }

  static int getIncreasedDelay(int count) {
    return 2000 + count * 100;
  }

  // create random integer between 0 and 1000
  static int getDefaultDelay() {
    return new Random().nextInt(1000);
  }

}

package com.baloise.open.devops.monitor.util;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.simple.SimpleConfig;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
public class LogMeterRegistry extends SimpleMeterRegistry {

  public LogMeterRegistry(Clock clock) {
    super(new SimpleConfig() {
      @Override
      public String prefix() {
        return "log";
      }

      @Override
      public String get(String key) {
        return null;
      }

      @Override
      public Duration step() {
        Duration duration = Duration.ofMillis(1);
        return duration;
      }
    }, clock);
  }

  @Override
  protected TimeUnit getBaseTimeUnit() {
    return TimeUnit.MILLISECONDS;
  }
}

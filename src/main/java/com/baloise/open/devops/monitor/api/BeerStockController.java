package com.baloise.open.devops.monitor.api;

import com.baloise.open.devops.monitor.util.LogMeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("v1/beer/stock")
public class BeerStockController {

  private MeterRegistry meterRegistry;
  private Counter counter;
  private Integer gauge;

  public BeerStockController(LogMeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    initMeters();
  }

  private void initMeters() {
    this.counter = this.meterRegistry.counter("beer.stock", "beer", "stock");
    this.gauge = this.meterRegistry.gauge("beer.stock", 0);
  }


  @GetMapping(value = "/{make}", produces = "application/json")
  public @ResponseBody int getStockCount(@PathVariable String make) {
    this.gauge = new Random().nextInt();
    this.counter.increment(1.0);
    System.out.println("getStockCount for " + make);
    return gauge;
  }

}

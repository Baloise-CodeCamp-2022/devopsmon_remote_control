package com.baloise.open.devops.monitor.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("v1/beer/stock")
public class BeerStockController {

  @GetMapping(value = "/{make}", produces = "application/json")
  public @ResponseBody int getStockCount(@PathVariable String make) {
    return new Random().nextInt();
  }

}

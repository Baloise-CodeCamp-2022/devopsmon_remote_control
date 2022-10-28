package com.baloise.open.devops.monitor.gate.infrastructure.web;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FakeControllerTest {


  @Test
  void cpuBasedDaley() {
    FakeController testee = new FakeController();
    assertEquals(500L, testee.getCpuUsageBasedDelay());
    testee.setCpuUsage(0);
    assertEquals(0L, testee.getCpuUsageBasedDelay());
    testee.setCpuUsage(1);
    assertEquals(100L, testee.getCpuUsageBasedDelay());
    testee.setCpuUsage(20);
    assertEquals(2000L, testee.getCpuUsageBasedDelay());
    testee.setCpuUsage(99);
    assertEquals(9900L, testee.getCpuUsageBasedDelay());
  }

  @Test
  void defaultDelayTest() {
    IntStream.of(150).forEach(x -> {
      int defaultDelay = FakeController.getDefaultDelay();
      assertAll(
          () -> assertTrue(defaultDelay > 0),
          () -> assertTrue(defaultDelay <= 1000)
      );
    });
  }

  @Test
  void increasedTest() {
    assertTrue(FakeController.isIncreasedDelayRequired(0));
    assertFalse(FakeController.isIncreasedDelayRequired(1));
    assertFalse(FakeController.isIncreasedDelayRequired(2));
    assertFalse(FakeController.isIncreasedDelayRequired(3));
    assertFalse(FakeController.isIncreasedDelayRequired(4));
    assertTrue(FakeController.isIncreasedDelayRequired(5));
    assertTrue(FakeController.isIncreasedDelayRequired(6));
    assertTrue(FakeController.isIncreasedDelayRequired(7));
    assertTrue(FakeController.isIncreasedDelayRequired(8));
    assertFalse(FakeController.isIncreasedDelayRequired(9));
    assertTrue(FakeController.isIncreasedDelayRequired(10));
    assertFalse(FakeController.isIncreasedDelayRequired(11));
    assertTrue(FakeController.isIncreasedDelayRequired(12));
    assertFalse(FakeController.isIncreasedDelayRequired(13));
    assertTrue(FakeController.isIncreasedDelayRequired(14));
    assertTrue(FakeController.isIncreasedDelayRequired(15));
    assertTrue(FakeController.isIncreasedDelayRequired(16));
    assertFalse(FakeController.isIncreasedDelayRequired(17));
    assertTrue(FakeController.isIncreasedDelayRequired(18));
    assertFalse(FakeController.isIncreasedDelayRequired(19));
    assertTrue(FakeController.isIncreasedDelayRequired(20));
  }
}
package com.baloise.open.devops.monitor.admin.infrastructure.web;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DevopsmonAdminControllerTest {

  @Test
  void defaultDelayTest() {
    IntStream.of(150).forEach(x -> {
      int defaultDelay = DevopsmonAdminController.getDefaultDelay();
      assertAll(
          () -> assertTrue(defaultDelay > 0),
          () -> assertTrue(defaultDelay <= 1000)
      );
    });
  }

  @Test
  void increasedTest() {
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(0));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(1));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(2));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(3));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(4));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(5));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(6));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(7));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(8));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(9));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(10));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(11));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(12));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(13));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(14));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(15));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(16));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(17));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(18));
    assertFalse(DevopsmonAdminController.isIncreasedDelayRequired(19));
    assertTrue(DevopsmonAdminController.isIncreasedDelayRequired(20));
  }
}
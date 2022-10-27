package com.baloise.open.devops.monitor.controller.infrastructure.drools.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CpuSaturation {
    private int value;
    private int warnThreshold;
    private int criticalThreshold;
}

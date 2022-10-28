package com.baloise.open.devops.monitor.controller.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RecommendedAction {
    private String instanceId;
    private String action;
    private List<Object> actionParameters;
}

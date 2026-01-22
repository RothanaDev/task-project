package com.chetraseng.taskflow_api.enums;

public enum TaskPriority {

  LOW("low"),
  MEDIUM("medium"),
  HIGH("high");

  private String name;

  TaskPriority(String name) {
  }

  public String getPriority() {
    return name;
  }
}

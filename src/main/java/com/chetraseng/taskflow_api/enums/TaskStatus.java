package com.chetraseng.taskflow_api.enums;

public enum TaskStatus {
  TODO("todo"),
  IN_PROGRESS("in_progress"),
  DONE("done");

  private String status;

  TaskStatus(String status) {

    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }

}

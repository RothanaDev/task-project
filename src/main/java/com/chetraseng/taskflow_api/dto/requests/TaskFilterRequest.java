package com.chetraseng.taskflow_api.dto.requests;

import com.chetraseng.taskflow_api.enums.TaskStatus;

import lombok.Data;

@Data
public class TaskFilterRequest {
  private TaskStatus status;
  private String name;
}

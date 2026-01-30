package com.chetraseng.taskflow_api.dto.responses;

import com.chetraseng.taskflow_api.enums.TaskPriority;
import com.chetraseng.taskflow_api.enums.TaskStatus;

import lombok.Data;

@Data
public class TaskListResponse {
  private Long id;
  private String name;
  private String description;
  private TaskStatus status;
  private String projectName;
  private Integer comment;
  private Integer attachment;
  private TaskPriority priority;
}

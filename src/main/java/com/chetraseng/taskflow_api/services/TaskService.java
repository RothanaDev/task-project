package com.chetraseng.taskflow_api.services;

import java.util.List;

import com.chetraseng.taskflow_api.dto.requests.TaskFilterRequest;
import com.chetraseng.taskflow_api.dto.responses.PaginationDto;
import com.chetraseng.taskflow_api.dto.responses.TaskListResponse;

public interface TaskService {
  List<TaskListResponse> listTasks(TaskFilterRequest request, PaginationDto pagination);

  List<TaskListResponse> listProjectTasks(Long projectId, TaskFilterRequest request, PaginationDto pagination);
}

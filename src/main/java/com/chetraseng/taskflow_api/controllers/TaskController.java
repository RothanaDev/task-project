package com.chetraseng.taskflow_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chetraseng.taskflow_api.dto.requests.TaskFilterRequest;
import com.chetraseng.taskflow_api.dto.responses.PaginationDto;
import com.chetraseng.taskflow_api.dto.responses.PaginationResponse;
import com.chetraseng.taskflow_api.dto.responses.TaskListResponse;
import com.chetraseng.taskflow_api.services.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(TaskController.BASE_URL)
@RequiredArgsConstructor
public class TaskController {
  public static final String BASE_URL = "/api/v1/tasks";

  private final TaskService taskService;

  @GetMapping
  public ResponseEntity<PaginationResponse<TaskListResponse>> getAllTasks(TaskFilterRequest request,
      @Validated PaginationDto pagination) {
    return ResponseEntity.ok(new PaginationResponse<>(taskService.listTasks(request, pagination), pagination));
  }

  @GetMapping("/project/{projectId}")
  public ResponseEntity<PaginationResponse<TaskListResponse>> getProjectTasks(@PathVariable Long projectId,
      TaskFilterRequest request, @Validated PaginationDto pagination) {
    return ResponseEntity
        .ok(new PaginationResponse<>(taskService.listProjectTasks(projectId, request, pagination), pagination));
  }

}

package com.chetraseng.taskflow_api.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.chetraseng.taskflow_api.dto.requests.TaskFilterRequest;
import com.chetraseng.taskflow_api.dto.responses.PaginationDto;
import com.chetraseng.taskflow_api.dto.responses.TaskListResponse;
import com.chetraseng.taskflow_api.mappers.TaskMapper;
import com.chetraseng.taskflow_api.models.TaskModel;
import com.chetraseng.taskflow_api.models.TaskModel_;
import com.chetraseng.taskflow_api.repositories.TaskRepository;
import com.chetraseng.taskflow_api.services.TaskService;
import com.chetraseng.taskflow_api.specs.TaskSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Override
  public List<TaskListResponse> listTasks(TaskFilterRequest request, PaginationDto pagination) {
    Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(),
        Sort.by(TaskModel_.CREATED_AT).descending());

    Specification<TaskModel> spec = constructTaskSpec(request);

    Page<TaskModel> tasks = taskRepository.findAll(spec, pageable);

    pagination.setTotal(tasks.getTotalElements());
    pagination.setTotalPages(tasks.getTotalPages());

    return tasks.stream().map(taskMapper::toTaskListResponse).toList();
  }

  @Override
  public List<TaskListResponse> listProjectTasks(Long projectId, TaskFilterRequest request, PaginationDto pagination) {
    Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(),
        Sort.by(TaskModel_.CREATED_AT).descending());

    Specification<TaskModel> spec = constructTaskSpec(request)
        .and(TaskSpecification.equalProjectId(projectId));

    Page<TaskModel> tasks = taskRepository.findAll(spec, pageable);

    pagination.setTotal(tasks.getTotalElements());
    pagination.setTotalPages(tasks.getTotalPages());

    return tasks.stream().map(taskMapper::toTaskListResponse).toList();
  }

  private final Specification<TaskModel> constructTaskSpec(TaskFilterRequest request) {
    Specification<TaskModel> spec = Specification.unrestricted();

    if (request.getStatus() != null) {
      spec = spec.and(TaskSpecification.equalStatus(request.getStatus()));
    }

    if (request.getName().length() > 0) {
      spec = spec.and(TaskSpecification.containsName(request.getName()));
    }

    return spec;
  }

}

package com.chetraseng.taskflow_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import com.chetraseng.taskflow_api.dto.responses.TaskListResponse;
import com.chetraseng.taskflow_api.models.TaskModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TaskMapper {
  @Mapping(target = "projectName", source = "project.name")
  @Mapping(target = "comment", expression = "java(task.getComments().size())")
  TaskListResponse toTaskListResponse(TaskModel task);
}

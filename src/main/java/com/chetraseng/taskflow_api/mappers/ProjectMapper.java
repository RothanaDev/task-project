package com.chetraseng.taskflow_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import com.chetraseng.taskflow_api.dto.requests.AddProjectRequest;
import com.chetraseng.taskflow_api.dto.responses.ProjectListResponse;
import com.chetraseng.taskflow_api.models.ProjectModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProjectMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  ProjectModel toProjectModel(AddProjectRequest request);

  ProjectListResponse toProjectListResponse(ProjectModel project);
}

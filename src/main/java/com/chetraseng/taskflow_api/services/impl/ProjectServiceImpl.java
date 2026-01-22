package com.chetraseng.taskflow_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chetraseng.taskflow_api.dto.requests.AddProjectRequest;
import com.chetraseng.taskflow_api.dto.responses.ProjectListResponse;
import com.chetraseng.taskflow_api.mappers.ProjectMapper;
import com.chetraseng.taskflow_api.models.ProjectModel;
import com.chetraseng.taskflow_api.repositories.ProjectRepository;
import com.chetraseng.taskflow_api.services.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  @Override
  public List<ProjectListResponse> listProjects() {
    return projectRepository.findAll().stream().map(projectMapper::toProjectListResponse).toList();
  }

  @Override
  public Long addProject(AddProjectRequest request) {
    ProjectModel project = projectMapper.toProjectModel(request);
    return projectRepository.save(project).getId();
  }
}

package com.chetraseng.taskflow_api.services;

import java.util.List;

import com.chetraseng.taskflow_api.dto.requests.AddProjectRequest;
import com.chetraseng.taskflow_api.dto.responses.ProjectListResponse;

public interface ProjectService {

  List<ProjectListResponse> listProjects();

  Long addProject(AddProjectRequest request);
}

package com.chetraseng.taskflow_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chetraseng.taskflow_api.dto.requests.AddProjectRequest;
import com.chetraseng.taskflow_api.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ProjectController.BASE_URL)
@RequiredArgsConstructor
public class ProjectController {
  public static final String BASE_URL = "/api/v1/projects";
  private final ProjectService projectService;

  @PostMapping
  ResponseEntity<Long> addProject(@Validated @RequestBody AddProjectRequest request) {
    return ResponseEntity.ok(projectService.addProject(request));
  }

}

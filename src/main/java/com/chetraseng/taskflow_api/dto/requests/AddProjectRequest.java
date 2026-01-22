package com.chetraseng.taskflow_api.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record AddProjectRequest(
    @NotBlank(message = "Project name is required") String name,
    String description) {
}

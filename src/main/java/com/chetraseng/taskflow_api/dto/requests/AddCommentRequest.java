package com.chetraseng.taskflow_api.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record AddCommentRequest(
    @NotBlank(message = "Comment content is required") String content) {

}

package com.chetraseng.taskflow_api.dto.responses;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}

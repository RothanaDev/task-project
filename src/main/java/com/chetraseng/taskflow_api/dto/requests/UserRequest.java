package com.chetraseng.taskflow_api.dto.requests;

import lombok.Builder;

@Builder
public record UserRequest(
        String firstname,
        String lastname,
        String email,
        String password

) {
}

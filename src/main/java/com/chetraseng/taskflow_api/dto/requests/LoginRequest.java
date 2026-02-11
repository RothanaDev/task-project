package com.chetraseng.taskflow_api.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email is Required")
        String email,
        @NotBlank(message = "Password Number is Required")
        String password
) {
}

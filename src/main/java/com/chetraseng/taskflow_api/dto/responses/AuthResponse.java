package com.chetraseng.taskflow_api.dto.responses;

import lombok.Builder;

@Builder
public record AuthResponse(

        String TokenType,
        String refreshToken,
        String accessToken
) {
}

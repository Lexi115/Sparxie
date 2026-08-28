package io.lexi115.sparxie.user.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record LoginResponse(
        @JsonIgnore UUID userId,
        String accessToken,
        @JsonIgnore String refreshToken
) {
}

package io.lexi115.sparxie.user.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record UserRegisterResponse(
        @JsonIgnore UUID userId,
        String accessToken,
        @JsonIgnore String refreshToken
) {
}

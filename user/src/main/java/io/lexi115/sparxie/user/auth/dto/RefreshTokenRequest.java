package io.lexi115.sparxie.user.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RefreshTokenRequest(
        @NotNull UUID userId,
        @NotBlank String refreshToken
) {
}

package io.lexi115.sparxie.user.auth.dto;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponse(
        UUID userId,
        String username,
        String email,
        Instant createdAt,
        String accessToken,
        String refreshToken
) {
}

package io.lexi115.sparxie.user.auth.dto;

import java.time.Instant;
import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String email,
        Instant createdAt,
        String accessToken,
        String refreshToken
) {
}

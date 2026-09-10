package io.lexi115.sparxie.user.auth.dto;

import lombok.Builder;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Builder
public record CallbackResponse(
        URI location,
        UUID userId,
        String username,
        String email,
        Instant createdAt,
        String accessToken,
        String refreshToken
) {
}

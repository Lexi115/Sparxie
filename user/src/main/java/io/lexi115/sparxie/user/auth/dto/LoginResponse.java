package io.lexi115.sparxie.user.auth.dto;

import io.lexi115.sparxie.user.auth.providers.IdentityProvider;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String email,
        Instant createdAt,
        List<IdentityProvider> providers,
        String accessToken,
        String refreshToken
) {
}

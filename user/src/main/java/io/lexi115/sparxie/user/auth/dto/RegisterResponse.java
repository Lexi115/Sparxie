package io.lexi115.sparxie.user.auth.dto;

import io.lexi115.sparxie.user.auth.provider.IdentityProvider;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RegisterResponse(
        UUID userId,
        String username,
        String email,
        Instant createdAt,
        List<IdentityProvider> providers,
        String accessToken,
        String refreshToken
) {
}

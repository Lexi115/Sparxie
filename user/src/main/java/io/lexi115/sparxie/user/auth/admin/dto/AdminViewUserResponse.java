package io.lexi115.sparxie.user.auth.admin.dto;

import io.lexi115.sparxie.user.auth.UserRole;
import io.lexi115.sparxie.user.auth.providers.IdentityProvider;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record AdminViewUserResponse(
        UUID userId,
        String username,
        String email,
        List<UserRole> roles,
        List<IdentityProvider> providers,
        Instant createdAt
) {
}

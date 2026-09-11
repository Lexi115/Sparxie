package io.lexi115.sparxie.user.auth.admin.dto;

import io.lexi115.sparxie.user.auth.UserRole;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record AdminCreateUserResponse(
        UUID userId,
        String username,
        String email,
        List<UserRole> roles,
        Instant createdAt
) {
}

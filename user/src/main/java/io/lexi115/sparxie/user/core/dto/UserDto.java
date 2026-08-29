package io.lexi115.sparxie.user.core.dto;

import java.time.Instant;
import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        Instant createdAt
) {
}

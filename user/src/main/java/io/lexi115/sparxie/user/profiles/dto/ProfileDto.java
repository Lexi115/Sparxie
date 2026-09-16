package io.lexi115.sparxie.user.profiles.dto;

import java.time.Instant;
import java.util.UUID;

public record ProfileDto(
        UUID id,
        String username,
        Instant createdAt
) {
}

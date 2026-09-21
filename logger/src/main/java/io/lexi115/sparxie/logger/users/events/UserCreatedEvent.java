package io.lexi115.sparxie.logger.users.events;

import java.time.Instant;
import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt,
        String provider
) {
}

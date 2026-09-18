package io.lexi115.sparxie.logger.users.events;

import java.time.Instant;
import java.util.UUID;

public record UserDeletedEvent(
        UUID userId,
        Instant deletedAt
) {
}

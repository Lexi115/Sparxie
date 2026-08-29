package io.lexi115.sparxie.user.core.event;

import io.lexi115.sparxie.user.messaging.Event;

import java.time.Instant;
import java.util.UUID;

public record UserDeletedEvent(
        UUID userId,
        Instant deletedAt
) implements Event {
}

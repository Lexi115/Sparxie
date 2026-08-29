package io.lexi115.sparxie.user.core.event;

import io.lexi115.sparxie.user.messaging.Event;

import java.time.Instant;
import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt
) implements Event {
}

package io.lexi115.sparxie.user.auth.event;

import io.lexi115.sparxie.user.event.Event;

import java.time.Instant;
import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt
) implements Event {
}

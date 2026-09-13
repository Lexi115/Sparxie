package io.lexi115.sparxie.inventory.players.events;

import java.time.Instant;
import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt
) {
}

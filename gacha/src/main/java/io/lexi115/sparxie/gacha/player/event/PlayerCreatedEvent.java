package io.lexi115.sparxie.gacha.player.event;

import java.time.Instant;
import java.util.UUID;

public record PlayerCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt
) {
}

package io.lexi115.sparxie.game.event;

import java.time.Instant;
import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String key,
        String topic,
        String payload,
        Instant createdAt,
        EventType type
) {
}

package io.lexi115.sparxie.game.messaging;

import java.time.Instant;
import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String topic,
        String payload,
        Instant createdAt
) {
}

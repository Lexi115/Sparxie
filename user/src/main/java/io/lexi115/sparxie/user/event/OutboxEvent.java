package io.lexi115.sparxie.user.event;

import java.time.Instant;
import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String topic,
        String payload,
        Instant createdAt
) {
}

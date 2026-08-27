package io.lexi115.sparxie.game.warp.event;

import io.lexi115.sparxie.game.messaging.Event;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record WarpPerformedEvent(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        Map<String, Long> items
) implements Event {
}

package io.lexi115.sparxie.game.warp.event;

import io.lexi115.sparxie.game.messaging.Event;

import java.time.Instant;
import java.util.List;

public record WarpPerformedEvent(
        String transactionId,
        String playerId,
        Instant createdAt,
        List<String> itemIds
) implements Event {
}

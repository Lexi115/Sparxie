package io.lexi115.sparxie.game.warp.event;

import io.lexi115.sparxie.game.event.Event;
import io.lexi115.sparxie.game.event.EventType;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record WarpPerformedEvent(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        Map<String, Long> items
) implements Event {
    @Override
    public EventType getEventType() {
        return EventType.WARP_PERFORMED;
    }
}

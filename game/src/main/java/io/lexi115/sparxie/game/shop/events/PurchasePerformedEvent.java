package io.lexi115.sparxie.game.shop.events;

import io.lexi115.sparxie.game.events.Event;
import io.lexi115.sparxie.game.events.EventType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PurchasePerformedEvent(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        String currency,
        BigDecimal price,
        String itemId,
        Long amount
) implements Event {
    @Override
    public EventType getEventType() {
        return EventType.PURCHASE_PERFORMED;
    }
}

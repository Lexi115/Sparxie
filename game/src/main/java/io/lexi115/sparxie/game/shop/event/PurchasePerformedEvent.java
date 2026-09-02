package io.lexi115.sparxie.game.shop.event;

import io.lexi115.sparxie.game.event.Event;
import io.lexi115.sparxie.game.event.EventType;
import io.lexi115.sparxie.game.shop.ShopCurrency;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PurchasePerformedEvent(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        ShopCurrency currency,
        BigDecimal price,
        String itemId,
        Long amount
) implements Event {
    @Override
    public EventType getEventType() {
        return EventType.PURCHASE_PERFORMED;
    }
}

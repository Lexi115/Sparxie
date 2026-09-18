package io.lexi115.sparxie.logger.shop.events;


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
) {
}

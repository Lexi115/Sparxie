package io.lexi115.sparxie.game.histories.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PurchaseHistoryEntry(
        UUID transactionId,
        Instant createdAt,
        String currency,
        BigDecimal price,
        String itemId,
        Long amount
) {
}

package io.lexi115.sparxie.logger.shop.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PurchaseHistory(
        UUID playerId,
        List<PurchaseHistoryEntry> entries
) {
}

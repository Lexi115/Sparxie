package io.lexi115.sparxie.game.histories.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PurchaseHistory(
        UUID playerId,
        List<PurchaseHistoryEntry> entries
) {
}

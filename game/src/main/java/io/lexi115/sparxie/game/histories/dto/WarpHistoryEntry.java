package io.lexi115.sparxie.game.histories.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record WarpHistoryEntry(
        UUID transactionId,
        Instant createdAt,
        List<WarpHistoryEntryItem> result
) {
}

package io.lexi115.sparxie.logger.warps.events;

import io.lexi115.sparxie.logger.warps.dto.WarpResponse;

import java.time.Instant;
import java.util.UUID;

public record WarpPerformedEvent(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        WarpResponse result
) {
}

package io.lexi115.sparxie.gacha.warp.transaction;

import io.lexi115.sparxie.gacha.warp.WarpResult;

import java.time.Instant;
import java.util.UUID;

public record WarpTransaction(
        UUID transactionId,
        UUID playerId,
        Instant createdAt,
        WarpResult result
) {
}

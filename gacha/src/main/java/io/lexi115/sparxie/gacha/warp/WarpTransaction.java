package io.lexi115.sparxie.gacha.warp;

import java.time.Instant;
import java.util.UUID;

public record WarpTransaction(
        UUID transactionId,
        UUID playerId,
        Instant timestamp,
        WarpResult result
) {
}

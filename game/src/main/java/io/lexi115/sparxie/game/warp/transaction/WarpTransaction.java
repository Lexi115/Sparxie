package io.lexi115.sparxie.game.warp.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WarpTransaction {
    private UUID transactionId;
    private UUID playerId;
    private Instant createdAt;

    @Setter
    private WarpTransactionStatus status;

    @Setter
    private Map<String, Long> items;

    public boolean isCompleted() {
        return this.status == WarpTransactionStatus.COMPLETED;
    }
}

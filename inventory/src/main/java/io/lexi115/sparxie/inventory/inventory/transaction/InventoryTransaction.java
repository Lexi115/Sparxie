package io.lexi115.sparxie.inventory.inventory.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransaction {
    private UUID transactionId;
    private UUID playerId;
    private Instant createdAt;

    @Setter
    private InventoryTransactionStatus status;

    public boolean isCompleted() {
        return this.status == InventoryTransactionStatus.COMPLETED;
    }
}

package io.lexi115.sparxie.inventory.inventory.transaction;

import java.util.Optional;
import java.util.UUID;

public interface InventoryTransactionRepository {
    Optional<InventoryTransaction> findById(UUID id);

    void save(InventoryTransaction transaction);
}

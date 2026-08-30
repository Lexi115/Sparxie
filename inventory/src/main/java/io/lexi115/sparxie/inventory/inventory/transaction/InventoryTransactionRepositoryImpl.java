package io.lexi115.sparxie.inventory.inventory.transaction;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InventoryTransactionRepositoryImpl implements InventoryTransactionRepository {

    private final Map<UUID, InventoryTransaction> map = new HashMap<>();

    @Override
    public Optional<InventoryTransaction> findById(final UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(final InventoryTransaction transaction) {
        map.put(transaction.getTransactionId(), transaction);
    }
}

package io.lexi115.sparxie.inventory.inventory.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryTransactionService {
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public InventoryTransaction getById(final UUID transactionId) {
        return inventoryTransactionRepository.findById(transactionId).orElse(null);
    }

    public void create(final UUID transactionId, final UUID playerId) {
        var transaction = InventoryTransaction.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .build();
        inventoryTransactionRepository.save(transaction);
    }
}

package io.lexi115.sparxie.inventory.inventory.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryTransactionService {
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public InventoryTransaction getOrCreateTransaction(final UUID transactionId, final UUID playerId) {
        var oldTransaction = inventoryTransactionRepository.findById(transactionId).orElse(null);
        if (oldTransaction != null) {
            return oldTransaction;
        }

        var newTransaction = new InventoryTransaction(transactionId, playerId, Instant.now(), InventoryTransactionStatus.PENDING);
        try {
            inventoryTransactionRepository.save(newTransaction);
            return newTransaction;
        } catch (Exception e) { // duplicate key
            return inventoryTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalStateException("Transaction should exist but wasn't found."));
        }
    }

    //@Transactional
    public void commitTransaction(final InventoryTransaction transaction) {
        transaction.setStatus(InventoryTransactionStatus.COMPLETED);
        inventoryTransactionRepository.save(transaction);
    }
}

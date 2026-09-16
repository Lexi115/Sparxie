package io.lexi115.sparxie.inventory.inventories.transactions;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InventoryTransactionRepository extends CrudRepository<InventoryTransaction, UUID> {
}

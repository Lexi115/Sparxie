package io.lexi115.sparxie.game.shop.transactions;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShopTransactionRepository extends CrudRepository<ShopTransaction, UUID> {
}

package io.lexi115.sparxie.game.shop.transaction;

import java.util.Optional;
import java.util.UUID;

public interface ShopTransactionRepository {
    Optional<ShopTransaction> findById(UUID id);

    void save(ShopTransaction shopTransaction);
}

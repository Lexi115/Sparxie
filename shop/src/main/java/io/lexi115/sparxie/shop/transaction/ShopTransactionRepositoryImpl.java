package io.lexi115.sparxie.shop.transaction;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShopTransactionRepositoryImpl implements ShopTransactionRepository {

    private final HashMap<UUID, ShopTransaction> map = new HashMap<>();

    @Override
    public Optional<ShopTransaction> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(ShopTransaction shopTransaction) {
        map.put(shopTransaction.getTransactionId(), shopTransaction);
    }
}

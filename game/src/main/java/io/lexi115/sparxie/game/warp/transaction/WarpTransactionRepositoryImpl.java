package io.lexi115.sparxie.game.warp.transaction;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class WarpTransactionRepositoryImpl implements WarpTransactionRepository {

    private final Map<UUID, WarpTransaction> map = new HashMap<>();

    @Override
    public Optional<WarpTransaction> getById(final UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(final WarpTransaction transaction) {
        map.put(transaction.getTransactionId(), transaction);
    }
}

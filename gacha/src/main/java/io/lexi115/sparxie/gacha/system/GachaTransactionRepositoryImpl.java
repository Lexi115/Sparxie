package io.lexi115.sparxie.gacha.system;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GachaTransactionRepositoryImpl implements GachaTransactionRepository {

    private final Map<Long, GachaTransaction> map = new HashMap<>();

    @Override
    public Optional<GachaTransaction> getById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(GachaTransaction transaction) {
        map.put(transaction.id(), transaction);
    }
}

package io.lexi115.sparxie.gacha.system;

import java.util.Optional;

public interface GachaTransactionRepository {
    Optional<GachaTransaction> getById(Long id);

    void save(GachaTransaction transaction);
}

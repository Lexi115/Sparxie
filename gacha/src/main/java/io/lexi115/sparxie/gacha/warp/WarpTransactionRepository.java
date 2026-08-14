package io.lexi115.sparxie.gacha.warp;

import java.util.Optional;
import java.util.UUID;

public interface WarpTransactionRepository {
    Optional<WarpTransaction> getById(UUID id);

    void save(WarpTransaction transaction);
}

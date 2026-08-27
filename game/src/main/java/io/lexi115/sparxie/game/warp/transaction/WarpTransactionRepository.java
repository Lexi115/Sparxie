package io.lexi115.sparxie.game.warp.transaction;

import java.util.Optional;
import java.util.UUID;

public interface WarpTransactionRepository {
    Optional<WarpTransaction> findById(UUID id);

    void save(WarpTransaction transaction);
}

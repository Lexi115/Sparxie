package io.lexi115.sparxie.inventory.player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
    Optional<Player> findById(UUID id);

    void save(Player player);
}

package io.lexi115.sparxie.gacha.player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
    Optional<Player> getById(UUID id);
    void save(Player player);
    void delete(Player player);
}

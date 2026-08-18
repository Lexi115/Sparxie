package io.lexi115.sparxie.inventory.player;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerRepositoryImpl implements PlayerRepository {

    private final HashMap<UUID, Player> map = new HashMap<>();

    public PlayerRepositoryImpl() {
        var players = List.of(
                new Player(UUID.fromString("1bbad0cc-4a14-4c5e-8254-f851ffa30907"))
        );
        players.forEach(p -> map.put(p.getId(), p));
    }

    @Override
    public Optional<Player> findById(final UUID id) {
        return Optional.ofNullable(map.get(id));
    }
}

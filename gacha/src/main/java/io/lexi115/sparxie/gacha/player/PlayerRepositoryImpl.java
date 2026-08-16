package io.lexi115.sparxie.gacha.player;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final Map<UUID, Player> map = new HashMap<>();

    public PlayerRepositoryImpl() {
        var players = List.of(
                new Player(UUID.fromString("1bbad0cc-4a14-4c5e-8254-f851ffa30907"), new PlayerPity())
        );
        players.forEach(p -> map.put(p.getId(), p));
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(Player player) {
        map.put(player.getId(), player);
    }

    @Override
    public void deleteById(UUID id) {
        map.remove(id);
    }
}

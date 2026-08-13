package io.lexi115.sparxie.gacha.player;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getById(final String id) {
        var uuid = UUID.fromString(id);
        return playerRepository.getById(uuid).orElse(null);
    }

    public void savePlayer(final Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(final String id) {
        var player = Optional.ofNullable(getById(id)).orElseThrow(PlayerNotFoundException::new);
        playerRepository.delete(player);
    }
}

package io.lexi115.sparxie.gacha.player;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getById(final UUID id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id.toString()));
    }

    public void savePlayer(final Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(final UUID id) {
        playerRepository.deleteById(id);
    }
}

package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.player.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getById(final UUID userId) {
        return playerRepository.findById(userId)
                .orElseThrow(() -> new PlayerNotFoundException(userId));
    }

    public void create(final UUID userId) {
        var player = new Player(userId);
        playerRepository.save(player);
    }

    public void save(final Player player) {
        playerRepository.save(player);
    }

    public void deleteById(final UUID userId) {
        playerRepository.deleteById(userId);
    }
}

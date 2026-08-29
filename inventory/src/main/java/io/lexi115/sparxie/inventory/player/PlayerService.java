package io.lexi115.sparxie.inventory.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public void createPlayer(final UUID userId) {
        var player = new Player(userId);
        playerRepository.save(player);
    }

    public Player getById(final UUID userId) {
        return playerRepository.findById(userId)
                .orElseThrow(() -> new PlayerNotFoundException(userId));
    }

    public void savePlayer(final Player player) {
        playerRepository.save(player);
    }

    public void deleteById(final UUID userId) {
        playerRepository.deleteById(userId);
    }
}

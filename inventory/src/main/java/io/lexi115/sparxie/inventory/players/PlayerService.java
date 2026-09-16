package io.lexi115.sparxie.inventory.players;

import io.lexi115.sparxie.inventory.players.exceptions.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getById(final UUID playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public boolean existsById(final UUID playerId) {
        return playerRepository.existsById(playerId);
    }

    @Transactional
    public void create(final UUID playerId) {
        var player = new Player(playerId);
        playerRepository.save(player);
    }

    public void save(final Player player) {
        playerRepository.save(player);
    }

    @Transactional
    public void deleteById(final UUID playerId) {
        var player = getById(playerId);
        playerRepository.delete(player);
    }
}

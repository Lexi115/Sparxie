package io.lexi115.sparxie.gacha.player;

import io.lexi115.sparxie.gacha.player.exception.PlayerAlreadyExistsException;
import io.lexi115.sparxie.gacha.player.exception.PlayerNotFoundException;
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

    @Transactional
    public void create(final UUID playerId) {
        if (playerRepository.existsById(playerId)) {
            throw new PlayerAlreadyExistsException(playerId);
        }
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

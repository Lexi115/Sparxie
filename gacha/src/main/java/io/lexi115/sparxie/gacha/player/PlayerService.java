package io.lexi115.sparxie.gacha.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getById(final UUID id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public void savePlayer(final Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(final UUID id) {
        playerRepository.deleteById(id);
    }
}

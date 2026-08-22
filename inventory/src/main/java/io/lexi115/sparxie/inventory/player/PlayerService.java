package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.core.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getById(final UUID id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id.toString()));
    }

    public void giveItem(final UUID playerId, final String itemId, final ItemType itemType, final Long amount) {
        var player = getById(playerId);
        player.giveItem(itemId, itemType, amount);
    }

    public void consumeItem(final UUID playerId, final String itemId, final ItemType itemType, final Long amount) {
        var player = getById(playerId);
        player.consumeItem(itemId, itemType, amount);
    }
}

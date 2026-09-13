package io.lexi115.sparxie.game.inventories;

import io.lexi115.sparxie.game.inventories.dto.MultipleItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryClient inventoryClient;

    public void giveItems(UUID transactionId, UUID playerId, Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, items);
        inventoryClient.giveItems(playerId, request);
    }

    public void consumeItems(UUID transactionId, UUID playerId, Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, items);
        inventoryClient.consumeItems(playerId, request);
    }

    public Map<String, Long> getCharacters(final UUID playerId, final Pageable pageable) {
        return inventoryClient.getCharacters(playerId, pageable);
    }

    public Map<String, Long> getWeapons(final UUID playerId, final Pageable pageable) {
        return inventoryClient.getWeapons(playerId, pageable);
    }

    public Map<String, Long> getMaterials(final UUID playerId, final Pageable pageable) {
        return inventoryClient.getMaterials(playerId, pageable);
    }
}

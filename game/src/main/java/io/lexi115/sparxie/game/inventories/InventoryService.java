package io.lexi115.sparxie.game.inventories;

import io.lexi115.sparxie.game.inventories.dto.MultipleItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryClient inventoryClient;

    public void giveItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount) {
        giveItems(transactionId, playerId, Map.of(itemId, amount));
    }

    public void giveItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, items);
        inventoryClient.giveItems(playerId, request);
    }

    public void consumeItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount) {
        consumeItems(transactionId, playerId, Map.of(itemId, amount));
    }

    public void consumeItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, items);
        inventoryClient.consumeItems(playerId, request);
    }

    public Map<String, Long> getCharacters(final UUID playerId, final Pageable pageable) {
        return inventoryClient.getCharacters(playerId, pageable);
    }

    public Map<String, Long> getWeapons(final UUID playerId, final Pageable pageable) {
        return inventoryClient.getWeapons(playerId, pageable);
    }

    public Map<String, Long> getMaterials(final UUID playerId, final Pageable pageable, final List<String> itemIds) {
        return inventoryClient.getMaterials(playerId, pageable, itemIds);
    }

    public Long getMaterialAmount(final UUID playerId, final String itemId) {
        var itemMap = inventoryClient.getMaterials(playerId, PageRequest.of(0, 1), List.of(itemId));
        return itemMap.getOrDefault(itemId, 0L);
    }
}

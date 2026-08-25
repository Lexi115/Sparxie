package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.MultipleItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryClient inventoryClient;

    public void giveItems(UUID transactionId, UUID playerId, Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, playerId, items);
        inventoryClient.giveItems(request);
    }

    public void consumeItems(UUID transactionId, UUID playerId, Map<String, Long> items) {
        var request = new MultipleItemsRequest(transactionId, playerId, items);
        inventoryClient.consumeItems(request);
    }
}

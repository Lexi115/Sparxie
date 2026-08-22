package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.ItemAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryClient inventoryClient;

    public void addItems(UUID transactionId, UUID playerId, Map<String, Long> items) {
        var request = new ItemAddRequest(transactionId, playerId, items);
        inventoryClient.addItems(request);
    }
}

package io.lexi115.sparxie.game.inventory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    private InventoryClient inventoryClient;

    public InventoryService(final InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public void addItems(UUID transactionId, UUID playerId, List<String> itemIds) {
        inventoryClient.addItems(transactionId, playerId, itemIds);
    }
}

package io.lexi115.sparxie.game.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryClient inventoryClient;

    public InventoryService(final InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public void addItems(String transactionId, String playerId, List<String> itemIds) {
        inventoryClient.addItems(transactionId, playerId, itemIds);
    }
}

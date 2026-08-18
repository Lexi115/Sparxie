package io.lexi115.sparxie.game.inventory;

import java.util.List;
import java.util.UUID;

public interface InventoryClient {
    void addItems(UUID transactionId, UUID playerId, List<String> itemIds);
}

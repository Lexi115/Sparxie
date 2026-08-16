package io.lexi115.sparxie.game.inventory;

import java.util.List;

public interface InventoryClient {
    void addItems(String transactionId, String playerId, List<String> itemIds);
}

package io.lexi115.sparxie.game.inventory;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InventoryClientImpl implements InventoryClient {

    private final Map<UUID, String> transactionsMap = new HashMap<>();

    @Override
    public void addItems(UUID transactionId, UUID playerId, List<String> itemIds) {
        if (transactionsMap.containsKey(transactionId)) {
            System.out.println("INVENTORY: already processed!");
            return;
        }
        System.out.println("INVENTORY: processing...");
        transactionsMap.put(transactionId, "done");
    }
}

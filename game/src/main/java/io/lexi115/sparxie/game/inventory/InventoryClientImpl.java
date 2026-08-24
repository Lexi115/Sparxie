package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.ItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Service
public class InventoryClientImpl implements InventoryClient {

    private final Map<UUID, String> transactionsMap = new HashMap<>();

    @Override
    public void giveItems(final ItemRequest request) {
        if (transactionsMap.containsKey(request.transactionId())) {
            System.out.println("INVENTORY: already processed!");
            return;
        }
        System.out.println("INVENTORY: processing...");
        transactionsMap.put(request.transactionId(), "done");
    }

    @Override
    public void consumeItems(ItemRequest request) {

    }
}

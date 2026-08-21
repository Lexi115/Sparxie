package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.ItemAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryClient inventoryClient;

    public void addItems(UUID transactionId, UUID playerId, List<String> itemIds) {
        var request = new ItemAddRequest(transactionId, playerId, itemIds);
        inventoryClient.addItems(request);
    }
}

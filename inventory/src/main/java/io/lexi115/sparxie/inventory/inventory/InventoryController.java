package io.lexi115.sparxie.inventory.inventory;

import io.lexi115.sparxie.inventory.inventory.dto.ItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/give")
    public ResponseEntity<Void> giveItems(@Valid @RequestBody final ItemRequest request) {
        inventoryService.giveItems(request.transactionId(), request.playerId(), request.items());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/consume")
    public ResponseEntity<Void> consumeItems(@Valid @RequestBody final ItemRequest request) {
        inventoryService.consumeItems(request.transactionId(), request.playerId(), request.items());
        return ResponseEntity.noContent().build();
    }
}

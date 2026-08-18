package io.lexi115.sparxie.inventory.core;

import io.lexi115.sparxie.inventory.core.dto.ItemAddRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PatchMapping
    public void addItems(@Valid @RequestBody final ItemAddRequest request) {
        inventoryService.addItems(request.transactionId(), request.playerId(), request.itemIds());
    }
}

package io.lexi115.sparxie.inventory.core;

import io.lexi115.sparxie.inventory.core.dto.ItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public void giveItems(@Valid @RequestBody final ItemRequest request) {
        inventoryService.giveItems(request.transactionId(), request.playerId(), request.items());
    }

    @PostMapping("/consume")
    public void consumeItems(@Valid @RequestBody final ItemRequest request) {
        inventoryService.consumeItems(request.transactionId(), request.playerId(), request.items());
    }
}

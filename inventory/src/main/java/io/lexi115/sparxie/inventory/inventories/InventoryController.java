package io.lexi115.sparxie.inventory.inventories;

import io.lexi115.sparxie.inventory.inventories.dto.ItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/{playerId}/give")
    public ResponseEntity<Void> giveItems(
            @PathVariable final UUID playerId,
            @Valid @RequestBody final ItemRequest request
    ) {
        inventoryService.giveItems(request.transactionId(), playerId, request.items());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playerId}/consume")
    public ResponseEntity<Void> consumeItems(
            @PathVariable final UUID playerId,
            @Valid @RequestBody final ItemRequest request
    ) {
        inventoryService.consumeItems(request.transactionId(), playerId, request.items());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{playerId}/characters")
    public Map<String, Long> getCharacters(@PathVariable final UUID playerId, final Pageable pageable) {
        return inventoryService.getCharacters(playerId, pageable);
    }

    @GetMapping("/{playerId}/weapons")
    public Map<String, Long> getWeapons(@PathVariable final UUID playerId, final Pageable pageable) {
        return inventoryService.getWeapons(playerId, pageable);
    }

    @GetMapping("/{playerId}/materials")
    public Map<String, Long> getMaterials(
            @PathVariable final UUID playerId,
            final Pageable pageable,
            @RequestParam(required = false) final List<String> itemIds
    ) {
        return inventoryService.getMaterials(playerId, pageable, itemIds == null ? null : itemIds::contains);
    }
}

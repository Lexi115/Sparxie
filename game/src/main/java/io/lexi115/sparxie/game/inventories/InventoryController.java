package io.lexi115.sparxie.game.inventories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/characters")
    public Map<String, Long> getCharacters(@RequestHeader("X-User-Id") final UUID playerId, final Pageable pageable) {
        return inventoryService.getCharacters(playerId, pageable);
    }

    @GetMapping("/weapons")
    public Map<String, Long> getWeapons(@RequestHeader("X-User-Id") final UUID playerId, final Pageable pageable) {
        return inventoryService.getWeapons(playerId, pageable);
    }

    @GetMapping("/materials")
    public Map<String, Long> getMaterials(
            @RequestHeader("X-User-Id") final UUID playerId,
            final Pageable pageable,
            @RequestParam(required = false) final List<String> itemIds
    ) {
        return inventoryService.getMaterials(playerId, pageable, itemIds);
    }
}

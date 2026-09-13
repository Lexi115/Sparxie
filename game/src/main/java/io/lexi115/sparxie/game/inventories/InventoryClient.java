package io.lexi115.sparxie.game.inventories;

import io.lexi115.sparxie.game.inventories.dto.MultipleItemsRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(
        name = "inventory",
        contextId = "inventoryClient",
        url = "${app.http.client-uri.inventories}",
        path = "/inventories"
)
public interface InventoryClient {
    @PostMapping("/{playerId}/give")
    void giveItems(@PathVariable final UUID playerId, @Valid @RequestBody MultipleItemsRequest request);

    @PostMapping("/{playerId}/consume")
    void consumeItems(@PathVariable final UUID playerId, @Valid @RequestBody MultipleItemsRequest request);

    @GetMapping("/{playerId}/characters")
    Map<String, Long> getCharacters(@PathVariable final UUID playerId, final Pageable pageable);

    @GetMapping("/{playerId}/weapons")
    Map<String, Long> getWeapons(@PathVariable final UUID playerId, final Pageable pageable);

    @GetMapping("/{playerId}/materials")
    Map<String, Long> getMaterials(@PathVariable final UUID playerId, final Pageable pageable);
}

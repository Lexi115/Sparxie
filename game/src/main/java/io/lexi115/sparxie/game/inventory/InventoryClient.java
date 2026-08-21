package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.ItemAddRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory", contextId = "inventoryClient", url = "${app.http.inventory-client-uri}")
public interface InventoryClient {
    @PostMapping
    void addItems(@Valid @RequestBody final ItemAddRequest request);
}

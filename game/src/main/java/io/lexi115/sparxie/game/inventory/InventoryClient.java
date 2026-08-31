package io.lexi115.sparxie.game.inventory;

import io.lexi115.sparxie.game.inventory.dto.MultipleItemsRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory", contextId = "inventoryClient", url = "${app.http.client-uri.inventory}")
public interface InventoryClient {
    @PostMapping("/give")
    void giveItems(@Valid @RequestBody MultipleItemsRequest request);

    @PostMapping("/consume")
    void consumeItems(@Valid @RequestBody MultipleItemsRequest request);
}

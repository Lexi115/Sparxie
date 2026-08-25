package io.lexi115.sparxie.shop.inventory;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "inventory", contextId = "inventoryClient", url = "${app.http.inventory-client-uri}")
public interface InventoryClient {
    @PostMapping("/give")
    void giveItems(@Valid @RequestBody MultipleItemsRequest request);

    @PostMapping("/consume")
    void consumeItems(@Valid @RequestBody MultipleItemsRequest request);
}

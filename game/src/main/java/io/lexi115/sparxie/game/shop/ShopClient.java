package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.core.PurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.ShopItemDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "shop", contextId = "shopClient", url = "${app.http.shop-client-uri}")
public interface ShopClient {
    @GetMapping("/items/{id}")
    ShopItemDto getItemById(@PathVariable String id);

    @PostMapping("/items/purchase")
    void purchaseItem(@Valid @RequestBody PurchaseRequest request);
}

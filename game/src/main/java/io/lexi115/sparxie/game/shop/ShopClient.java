package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.shop.dto.ShopPurchasableItem;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "shop", contextId = "shopClient", url = "${app.http.client-uri.shop}")
public interface ShopClient {
    @GetMapping("/items/{id}")
    ShopPurchasableItem getItemById(@PathVariable String id);

    @PostMapping("/items/purchase")
    ShopPurchaseResponse purchaseItem(@Valid @RequestBody ShopPurchaseRequest request);
}

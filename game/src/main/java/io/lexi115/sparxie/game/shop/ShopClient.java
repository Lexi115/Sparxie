package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.shop.dto.ShopPurchasableItem;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(
        name = "shop",
        contextId = "shopClient",
        url = "${app.http.client-uri.shop}",
        path = "/shop"
)
public interface ShopClient {
    @GetMapping("/items/{itemId}")
    ShopPurchasableItem getItemById(@PathVariable String itemId);

    @PostMapping("/purchases")
    ShopPurchaseResponse purchaseItem(
            @Valid @RequestBody ShopPurchaseRequest request,
            @RequestHeader("X-User-Id") UUID playerId
    );
}

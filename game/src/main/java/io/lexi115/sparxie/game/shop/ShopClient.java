package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.inventory.dto.SingleItemRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseJadesRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "shop", contextId = "shopClient", url = "${app.http.shop-client-uri}")
public interface ShopClient {
    @PostMapping("/purchaseJades")
    void purchaseJades(@Valid @RequestBody PurchaseJadesRequest request);

    @PostMapping("/exchangeWithJades")
    void exchangeWithJades(@Valid @RequestBody SingleItemRequest request);
}

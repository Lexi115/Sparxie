package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.inventory.dto.SingleItemRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseJadesRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping("/purchaseJades")
    public void purchaseJades(@Valid @RequestBody final PurchaseJadesRequest request) {
        shopService.purchaseJades(request);
    }

    @PostMapping("/exchangeWithJades")
    public void exchangeWithJades(@Valid @RequestBody final SingleItemRequest request) {
        shopService.exchangeWithJades(request);
    }
}

package io.lexi115.sparxie.shop.core;

import io.lexi115.sparxie.shop.core.dto.ItemRequest;
import io.lexi115.sparxie.shop.core.dto.PurchaseJadesRequest;
import io.lexi115.sparxie.shop.game.GameCurrency;
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
        shopService.buyItem(
                request.transactionId(), request.playerId(), GameCurrency.STELLAR_JADE.name().toLowerCase(),
                request.amount(), BuyMethod.FAKE_MONEY);
    }

    @PostMapping("/exchangeWithJades")
    public void exchangeWithJades(@Valid @RequestBody final ItemRequest request) {
        shopService.buyItem(
                request.transactionId(), request.playerId(), request.itemId(), request.amount(), BuyMethod.JADES);
    }
}

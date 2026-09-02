package io.lexi115.sparxie.game.shop.dto;

import io.lexi115.sparxie.game.shop.ShopCurrency;

import java.math.BigDecimal;

public record PurchaseResponse(
        String itemId,
        Long amount,
        ShopCurrency currency,
        BigDecimal price
) {
}

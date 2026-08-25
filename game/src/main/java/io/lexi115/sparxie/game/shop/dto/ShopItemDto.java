package io.lexi115.sparxie.game.shop.dto;

import io.lexi115.sparxie.game.shop.ShopCurrency;

public record ShopItemDto(
        String id,
        ShopCurrency currency,
        Long cost
) {
}

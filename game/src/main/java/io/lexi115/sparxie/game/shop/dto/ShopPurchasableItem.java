package io.lexi115.sparxie.game.shop.dto;

public record ShopPurchasableItem(
        String id,
        String currency,
        Long cost
) {
}

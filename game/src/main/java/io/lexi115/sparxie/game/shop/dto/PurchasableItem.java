package io.lexi115.sparxie.game.shop.dto;

public record PurchasableItem(
        String id,
        String currency,
        Long cost
) {
}

package io.lexi115.sparxie.game.game.dto;

public record PurchasableItem(
        String id,
        String currency,
        Long cost
) {
}

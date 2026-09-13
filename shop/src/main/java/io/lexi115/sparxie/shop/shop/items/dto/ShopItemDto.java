package io.lexi115.sparxie.shop.shop.items.dto;

public record ShopItemDto(
        String itemId,
        String currency,
        Long cost
) {
}

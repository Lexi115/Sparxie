package io.lexi115.sparxie.shop.shop.item.dto;

public record ShopItemDto(
        String itemId,
        String currency,
        Long cost
) {
}

package io.lexi115.sparxie.shop.shop.dto;

import java.math.BigDecimal;

public record PurchaseResponseDto(
        String itemId,
        Long amount,
        String currency,
        BigDecimal price
) {
}

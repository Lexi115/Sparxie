package io.lexi115.sparxie.game.shop.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        String itemId,
        Long amount,
        String currency,
        BigDecimal price
) {
}

package io.lexi115.sparxie.game.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PurchaseRequest(
        @NotNull UUID transactionId,
        @NotNull UUID playerId,
        @NotBlank String itemId,
        @NotNull @Min(1) Long amount
) {
}

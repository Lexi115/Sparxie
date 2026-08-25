package io.lexi115.sparxie.game.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PurchaseJadesRequest(
        @NotNull UUID transactionId,
        @NotNull UUID playerId,
        @NotNull @Min(1) Long amount
) {
}

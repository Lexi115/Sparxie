package io.lexi115.sparxie.shop.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemRequest(
        @NotNull UUID transactionId,
        @NotNull UUID playerId,
        @NotNull String itemId,
        @NotNull @Min(1) Long amount
) {
}

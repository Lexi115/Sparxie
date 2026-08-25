package io.lexi115.sparxie.shop.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record MultipleItemsRequest(
        @NotNull UUID transactionId,
        @NotNull UUID playerId,
        @NotEmpty Map<@NotBlank String, @NotNull @Min(1) Long> items
) {
}

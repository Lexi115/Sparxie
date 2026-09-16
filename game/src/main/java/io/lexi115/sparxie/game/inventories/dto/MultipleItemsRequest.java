package io.lexi115.sparxie.game.inventories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record MultipleItemsRequest(
        @NotNull UUID transactionId,
        @NotEmpty Map<@NotBlank String, @NotNull @Min(1) Long> items
) {
}

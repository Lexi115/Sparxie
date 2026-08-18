package io.lexi115.sparxie.inventory.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ItemAddRequest(
        @NotBlank UUID transactionId,
        @NotBlank UUID playerId,
        @NotNull List<String> itemIds
) {
}
